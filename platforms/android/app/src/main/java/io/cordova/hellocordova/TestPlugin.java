package io.cordova.hellocordova;

import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;

public class TestPlugin extends CordovaPlugin {
    @Override
    public boolean execute (String action, String rawArgs, CallbackContext callbackContext)
    throws JSONException {
        if(action.equals("showToast")){
            this.showDialog(rawArgs);
            return true;
        }

        return super.execute(action, rawArgs, callbackContext);
    }
    private void showDialog(String params){
        Toast.makeText(cordova.getContext(),params,Toast.LENGTH_LONG).show();
    }   
}
