package com.insurgencedev.rivalswordsaddon;

import com.insurgencedev.rivalswordsaddon.listeners.RivalSwordsEventListener;
import org.insurgencedev.insurgenceboosters.api.addon.IBoostersAddon;
import org.insurgencedev.insurgenceboosters.api.addon.InsurgenceBoostersAddon;
import org.insurgencedev.insurgenceboosters.libs.fo.Common;

@IBoostersAddon(name = "RivalSwordsAddon", version = "1.0.3", author = "InsurgenceDev", description = "RivalMobSwords Support")
public class RivalSwordsAddon extends InsurgenceBoostersAddon {

    @Override
    public void onAddonReloadAblesStart() {
        if (Common.doesPluginExist("RivalMobSwords")) {
            registerEvent(new RivalSwordsEventListener());
        }
    }
}
