import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.*;

public class App implements Watcher {
    private final ZooKeeper zooKeeper;
    private Process process;

    public App() throws IOException {
        this.zooKeeper = new ZooKeeper("localhost:2182", 3000, this);
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        App app = new App();
        app.run();
    }

    public void run() throws InterruptedException, KeeperException {
        zooKeeper.addWatch("/z", AddWatchMode.PERSISTENT_RECURSIVE);
        while (true) {

        }
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.NodeCreated) {
            System.out.println("zNode created, path: " + event.getPath());
            try {
                printTree("/z");
            } catch (IOException | InterruptedException | KeeperException e) {
                e.printStackTrace();
            }


            if (Objects.equals(event.getPath(), "/z")) {
                System.out.println("Opening app...");
                String appLocation = "/System/Applications/Notes.app";
                try {
//                    Process process = Runtime.getRuntime().exec("chmod -R 777 " + appLocation);
                    process = Runtime.getRuntime().exec("open " + appLocation);
                    System.out.println("app: " + process);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    System.out.println("zNode /z number of children: " + zooKeeper.getAllChildrenNumber("/z"));
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (event.getType() == Event.EventType.NodeDeleted) {
            System.out.println("zNode deleted, path: " + event.getPath());
            if (Objects.equals(event.getPath(), "/z")) {
                System.out.println("Killing app");
                process.destroy();
            }
        }

    }

    public void printTree(String nodePath) throws IOException, InterruptedException, KeeperException {
        List<String> tree = getTree(nodePath);
        for (String child : tree) {
            StringTokenizer tokenizer = new StringTokenizer(child, "/");
            String token = null;
            while (tokenizer.hasMoreTokens()) {
                if (token != null)
                    System.out.print("\t");
                token = tokenizer.nextToken();
            }
            if (token != null)
                System.out.println("/" + token);
        }
    }

    public List<String> getTree(String nodePath) throws InterruptedException, KeeperException {
        return DFS(nodePath, new ArrayList<>());
    }

    List<String> DFS(String node, List<String> tree) throws InterruptedException, KeeperException {
        Stack<String> stack = new Stack<>();
        stack.push(node);
        while (!stack.empty()) {
            String current = stack.pop();
            for (String child : zooKeeper.getChildren(current, false)) {
                stack.push(String.join("/", current, child));
            }
            tree.add(current);
        }
        return tree;
    }
}
