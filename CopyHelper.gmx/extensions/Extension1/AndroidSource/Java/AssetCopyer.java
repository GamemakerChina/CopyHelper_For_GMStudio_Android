package com.ticktick.testassets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
 
public class AssetCopyer { 
	private static final String ASSET_LIST_FILENAME = "assets.lst";

	private final Context mContext;
	private final AssetManager mAssetManager;
	private File mAppDirectory;	
	
	public AssetCopyer( Context context ) {
		mContext = context;
		mAssetManager = context.getAssets();		
	}
		
	
	public boolean copy() throws IOException {

		List<String> srcFiles = new ArrayList<String>();
		
		mAppDirectory = mContext.getExternalFilesDir(null);
        if (null == mAppDirectory) {
        	return false;
        }
		
        List<String> assets = getAssetsList(); 
        for( String asset : assets ) {      
        	if( ! new File(mAppDirectory,asset).exists() ) {
        		srcFiles.add(asset);
        	}
        }        	  
        
        for( String file : srcFiles ) {
			copy(file);
		}
		
		return true;
	}

	protected List<String> getAssetsList() throws IOException {

		List<String> files = new ArrayList<String>();
		
		InputStream listFile = mAssetManager.open(new File(ASSET_LIST_FILENAME).getPath());
		BufferedReader br = new BufferedReader(new InputStreamReader(listFile));
		String path;
        while (null != (path = br.readLine())) {
        	files.add(path);
    	}
        
        return files;
	}
    

	protected File copy( String asset ) throws IOException {
		
        InputStream source = mAssetManager.open(new File(asset).getPath());
        File destinationFile = new File(mAppDirectory, asset);
        destinationFile.getParentFile().mkdirs();
        OutputStream destination = new FileOutputStream(destinationFile);
        byte[] buffer = new byte[1024];
        int nread;

        while ((nread = source.read(buffer)) != -1) {
            if (nread == 0) {
                nread = source.read();
                if (nread < 0)
                    break;
                destination.write(nread);
                continue;
            }
            destination.write(buffer, 0, nread);
        }
        destination.close();
        
        return destinationFile;
    }
}
