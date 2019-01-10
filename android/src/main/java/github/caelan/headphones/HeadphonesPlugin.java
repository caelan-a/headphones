package github.caelan.headphones;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import android.media.AudioManager;
import android.media.AudioDeviceInfo;
import android.content.Context;

/** HeadphonesPlugin */
public class HeadphonesPlugin implements MethodCallHandler {
  static String CHANNEL_NAME = "github.caelana.headphones";
  Context context;

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), CHANNEL_NAME);
    channel.setMethodCallHandler(new HeadphonesPlugin(registrar.context()));
  }

  HeadphonesPlugin(Context context) {
    this.context = context;
  }

  private boolean isHeadphonesPlugged() {
    AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    AudioDeviceInfo[] audioDevices = audioManager.getDevices(AudioManager.GET_DEVICES_ALL);
    for (AudioDeviceInfo deviceInfo : audioDevices) {
      if (deviceInfo.getType() == AudioDeviceInfo.TYPE_WIRED_HEADPHONES
          || deviceInfo.getType() == AudioDeviceInfo.TYPE_WIRED_HEADSET) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("isUsingHeadphones")) {
      if (isHeadphonesPlugged()) {
        result.success("true");
      } else {
        result.success("false");
      }
    } else {
      result.notImplemented();
    }
  }
}
