package org.maxing.learning.jdbc.service;

import jdk.dynalink.Operation;
import org.maxing.learning.jdbc.dao.OperationLogDao;
import org.maxing.learning.jdbc.domain.OperationLog;
import org.maxing.learning.jdbc.util.SqlSession;
import org.maxing.learning.jdbc.util.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationLogService {
    private final SqlSessionFactory sessionFactory;

    @Autowired
    public OperationLogService(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        System.out.println("[Service]:construct OperationLogService successfully，SqlSessionFactory="+sessionFactory);
    }

    public void writeOperationLog(OperationLog log){
        try(SqlSession session=sessionFactory.openSession()){
            OperationLogDao dao = session.getMapper(OperationLogDao.class);
            dao.writeLog(log);
        }
    }

    public OperationLog findLogByIdForUpdate(Long id){
        try(SqlSession session=sessionFactory.openSession()){
            OperationLogDao dao = session.getMapper(OperationLogDao.class);
            return dao.findByIdForUpdate(id);
        }
    }
    public List<OperationLog> findLogByUsername(String username){
        try(SqlSession session=sessionFactory.openSession()){
            OperationLogDao dao = session.getMapper(OperationLogDao.class);
            return dao.findByUsername(username);
        }
    }
}
