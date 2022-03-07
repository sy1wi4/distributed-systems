package com.example.lab2.zad3;

import com.example.lab2.zad2.Todo;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;

public class TodoResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String id;

    public TodoResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    @GET
    @Produces({MediaType.TEXT_XML})
    public Todo getTodoHTML() {
        return TodoDao.getModel().get(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Todo getTodo() {
        return TodoDao.getModel().get(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putTodo(JAXBElement<Todo> todo) {
        Todo c = todo.getValue();
        return putAndGetResponse(c);
    }

    @DELETE
    public void deleteTodo() {
        Todo c = TodoDao.getModel().remove(id);
    }

    private Response putAndGetResponse(Todo todo) {
        Response res;
        if (TodoDao.getModel().containsKey(todo.getId()))
            res = Response.noContent().build();
        else
            res = Response.created(uriInfo.getAbsolutePath()).build();
        TodoDao.getModel().put(todo.getId(), todo);
        return res;
    }
}
