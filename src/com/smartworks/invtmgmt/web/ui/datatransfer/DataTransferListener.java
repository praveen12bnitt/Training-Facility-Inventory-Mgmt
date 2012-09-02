package com.smartworks.invtmgmt.web.ui.datatransfer;

import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.record.EOFRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RowRecord;

public class DataTransferListener implements HSSFListener {
	@Override
	public void processRecord(Record record) {
		 
		switch (record.getSid())
        {
        	case RowRecord.sid:
            RowRecord rowrec = (RowRecord) record;
            System.out.println("Row found, first column at "
                    + rowrec.getFirstCol() + " last column at " + rowrec.getLastCol());
            break;
        	case NumberRecord.sid:
                NumberRecord numrec = (NumberRecord) record;
                System.out.println("Cell found with value " + numrec.getValue()
                        + " at row " + numrec.getRow() + " and column " + numrec.getColumn());
                break;
        	
        	
           	case EOFRecord.sid:
        	 System.out.println("EOF ..........");
        	 break;
        }
	}
}
