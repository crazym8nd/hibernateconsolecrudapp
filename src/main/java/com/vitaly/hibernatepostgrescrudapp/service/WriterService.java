package com.vitaly.hibernatepostgrescrudapp.service;
//  14-Jan-24
// gh crazym8nd


import com.vitaly.hibernatepostgrescrudapp.dao.WriterDao;
import com.vitaly.hibernatepostgrescrudapp.model.Writer;

import java.util.List;

public class WriterService {
    private final WriterDao writerDao;
    public WriterService(WriterDao writerDao){this.writerDao = writerDao;}
    public WriterService(){this.writerDao = new WriterDao();}

    public List<Writer> getWriters(){
        return writerDao.getWriters();
    }

    public Writer getWriter(Integer writerID){
        return writerDao.getWriterById(writerID);
    }

    public Writer saveWriter(Writer writer){
        return writerDao.saveWriter(writer);
    }

    public Writer update(Writer wrtier){
        return writerDao.update(wrtier);
    }

    public void deleteById(Integer writerId){
        writerDao.deleteById(writerId);
    }
}
