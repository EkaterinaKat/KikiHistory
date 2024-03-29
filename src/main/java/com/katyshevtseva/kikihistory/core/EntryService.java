package com.katyshevtseva.kikihistory.core;

import com.katyshevtseva.general.Page;
import com.katyshevtseva.kikihistory.core.model.Entry;
import com.katyshevtseva.kikihistory.core.model.EntryType;

import java.util.Date;

public class EntryService {

    public static void saveEntry(Entry existing, String val1, String val2, String image1, EntryType type) {
        boolean newOne = existing == null;
        if (existing == null) {
            existing = new Entry();
            existing.setCreationDate(new Date());
        }
        existing.setType(type);
        existing.setValue1(val1.trim());
        existing.setValue2(val2.trim());
        existing.setImage1(image1);
        Dao.save(newOne, existing);
    }

    public static void delete(Entry entry) {
        Dao.delete(entry);
    }

    public static Page<Entry> getPage(int pageNum) {
        return Dao.getEntries(pageNum, 20);
    }
}
