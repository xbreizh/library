package org.library.impl;

import org.library.contract.BookManager;
import org.library.contract.LoanManager;

import javax.inject.Named;
import java.util.logging.Logger;

@Named
public class LoanManagerImpl implements LoanManager {
    private Logger logger = Logger.getLogger(this.getClass().getName());

}
