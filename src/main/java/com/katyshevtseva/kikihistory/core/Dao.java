package com.katyshevtseva.kikihistory.core;

import com.katyshevtseva.general.Page;
import com.katyshevtseva.hibernate.CoreDao;
import com.katyshevtseva.kikihistory.core.model.Entry;
import com.katyshevtseva.kikihistory.core.model.EntryType;
import org.hibernate.criterion.Order;

public class Dao {
    private static final CoreDao coreDao = new CoreDao();

    public static <T> void save(boolean newOne, T t) {
        coreDao.save(newOne, t);
    }

    public static <T> void delete(T t) {
        coreDao.delete(t);
    }

    public static Page<Entry> getEntriesByType(EntryType type, int pageNum, int pageSize) {
        return coreDao.findBy(Entry.class, "type", type, pageNum, pageSize, Order.desc("id"));
    }
}
