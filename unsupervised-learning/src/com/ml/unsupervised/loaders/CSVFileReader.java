package com.ml.unsupervised.loaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import shared.DataSet;
import shared.DataSetDescription;
import shared.Instance;
import shared.reader.DataSetReader;

public class CSVFileReader  {
	
	private String header;

	/**
	 * 
	 * @param fileName
	 * @param skipHeader
	 * @return
	 * @throws Exception
	 */
	public DataSet read(String fileName, boolean skipHeader) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        List<Instance> data = new ArrayList<Instance>();
        Pattern pattern = Pattern.compile("[ ,]+");
        int lineNum = 0;
        while ((line = br.readLine()) != null) {
        	if(skipHeader && ++lineNum == 1) {
        		this.header = line;
        		continue;
        	}
            String[] split = pattern.split(line.trim());
            double[] input = new double[split.length];
            for (int i = 0; i < input.length; i++) {
                input[i] = Double.parseDouble(split[i]);
            }
            Instance instance = new Instance(input);
            data.add(instance);
        }
        br.close();
        Instance[] instances = (Instance[]) data.toArray(new Instance[0]);
        DataSet set = new DataSet(instances);
        set.setDescription(new DataSetDescription(set));
        return set;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

}
