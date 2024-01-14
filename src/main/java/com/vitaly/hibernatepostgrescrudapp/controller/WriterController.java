package com.vitaly.hibernatepostgrescrudapp.controller;
//  14-Jan-24
// gh crazym8nd


import com.vitaly.hibernatepostgrescrudapp.model.Post;
import com.vitaly.hibernatepostgrescrudapp.model.Writer;
import com.vitaly.hibernatepostgrescrudapp.service.WriterService;


import java.util.List;

public class WriterController {
    private final WriterService writerService = new WriterService();

    public Writer createWriter(Writer writer){
        return writerService.saveWriter(writer);
    }

    public List<Writer> getWriters(){
        return writerService.getWriters();
    }
    public Writer getWriter(Integer writerId){
        return writerService.getWriter(writerId);
    }

    public Writer updateWriter(Writer writer){
        return writerService.update(writer);
    }

    public void deleteById(Integer writerId){
        writerService.deleteById(writerId);
    }

}
