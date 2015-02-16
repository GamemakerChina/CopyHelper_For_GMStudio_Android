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
	private static final int EVENT_OTHER_SOCIAL = 70;

	public void star()
		{
		mAssetCopyer = new AssetCopyer(RunnerJNILib.ms_context);
		    new Thread(new Runnable() {			
				public void run() 
					{
					try 
						{
						 boolean _state =  mAssetCopyer.copy() ;
						 sendCopyFinishEvent(_state);
						} 
					catch (IOException e) 
						{			
						e.printStackTrace();
						sendCopyFinishEvent(false);
						}
						
					}
			}).start();
		}
	public String getDirectory()
		{
		String _packName =  RunnerJNILib.ms_context.getPackageName()  ;
		return 	"/sdcard/Android/data/" +  _packName  + "/files";
		}

	private void sendCopyFinishEvent( boolean _bLoaded )
		{
		int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
		RunnerJNILib.DsMapAddString( dsMapIndex, "type", "copy_result" );
		double loaded = (_bLoaded) ? 1 : 0;
		RunnerJNILib.DsMapAddDouble( dsMapIndex, "result", loaded);
		RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex,EVENT_OTHER_SOCIAL);
		}

    }
