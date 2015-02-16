package ${YYAndroidPackageName};

import ${YYAndroidPackageName}.R;
import ${YYAndroidPackageName}.RunnerActivity;
import com.yoyogames.runner.RunnerJNILib;

import com.ticktick.testassets.AssetCopyer; 
import java.io.IOException;
import android.os.Bundle;
import android.app.Activity;


public class CopyerExtension 
	{
	private AssetCopyer mAssetCopyer;

	public void star()
		{
		mAssetCopyer = new AssetCopyer(RunnerJNILib.ms_context);
		    new Thread(new Runnable() {			
				public void run() 
					{
					try 
						{
						mAssetCopyer.copy();
						} 
					catch (IOException e) 
						{			
						e.printStackTrace();
						}
						
					}
			}).start();
		}
	public String getDirectory()
		{
		String _packName =  RunnerJNILib.ms_context.getPackageName()  ;
		return 	"/sdcard/Android/data/" +  _packName  + "/files";
		}

    }
