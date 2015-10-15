package org.apache.cordova.notificationicon;

import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import me.leolin.shortcutbadger.ShortcutBadger;

public class NotificationIcon extends CordovaPlugin{
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws
		JSONException{
			if(action.equals("setIconCount")){
				this.setNotificationCount(args.getString(0), callbackContext);
				return true;
			}
			return false;
	}

	private void setNotificationCount(String count, CallbackContext callbackContext){
		try{
			int c = Integer.parseInt(count);
			if(c >= 0){
				ShortcutBadger.with(getApplicationContext()).count(c);
				callbackContext.success("set notification count successful");
			}else{
				callbackContext.success("notification count cannot be less than 0");
			}
		}catch(NumberFormatException nfe){
			callbackContext.success("notification count given is not an integer");
		}	
	}
}