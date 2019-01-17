package org.library.impl;

import org.library.contract.BookManager;

import javax.inject.Named;
import java.util.logging.Logger;

@Named
public class BookManagerImpl implements BookManager {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void trok() {
        logger.info("topoko");
    }


}
