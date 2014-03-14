package org.sejmngram.dbinserter;

import java.io.IOException;
import java.util.HashMap;

import org.sejmngram.dbinserter.blobs.BlobCreator;
import org.sejmngram.dbinserter.db.DatabaseRepo;
import org.sejmngram.dbinserter.model.RowData;

/**
 * Created by michalsiemionczyk on 14/02/14.
 */
public class Inserter {

    public static void main (String[] args){


        //read dokuemnt
//        "./scripts/sejmometr/downloadedData/2011-11-19.json"
//        ArrayList<Dokument> d = ins.getDokumentFromJsonFile("./scripts/sejmometr/downloadedData/");
//        ArrayList<Dokument> d = JsonFilesReader.getDokumentFromJsonFile("./scripts/sejmometr/dataFromCorpus/", 0);

        //get map of blobs
        HashMap<String, RowData> blobMap = null;
        try {
            blobMap = BlobCreator.getMapOfBlobs("../scripts/dataFromCorpus/", 1, true);
        } catch (IOException e) {
            e.printStackTrace();
        }


        BlobCreator.performAnalysis( blobMap );

        DatabaseRepo dbRepo = new DatabaseRepo();
        dbRepo.insertToDb(blobMap);
    }

}
