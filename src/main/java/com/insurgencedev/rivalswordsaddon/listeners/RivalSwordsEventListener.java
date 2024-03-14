package com.insurgencedev.rivalswordsaddon.listeners;

import me.rivaldev.mobsword.rivalmobswords.api.SwordEssenceReceivePreEnchantEvent;
import me.rivaldev.mobsword.rivalmobswords.api.SwordXPGainEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.insurgencedev.insurgenceboosters.api.IBoosterAPI;
import org.insurgencedev.insurgenceboosters.data.BoosterFindResult;

public final class RivalSwordsEventListener implements Listener {

    @EventHandler
    private void onReceive(SwordEssenceReceivePreEnchantEvent event) {
        final String TYPE = "Essence";
        final String NAMESPACE = "RIVAL_SWORDS";
        final double[] totalMulti = {0};

        BoosterFindResult pResult = IBoosterAPI.INSTANCE.getCache(event.getPlayer()).getBoosterDataManager().findActiveBooster(TYPE, NAMESPACE);
        if (pResult instanceof BoosterFindResult.Success boosterResult) {
            totalMulti[0] += boosterResult.getBoosterData().getMultiplier();
        }

        IBoosterAPI.INSTANCE.getGlobalBoosterManager().findGlobalBooster(TYPE, NAMESPACE, globalBooster -> {
            totalMulti[0] += globalBooster.getMultiplier();
            return null;
        }, () -> null);

        if (totalMulti[0] > 0) {
            event.setEssence(calculateAmount(event.getEssence(), totalMulti[0]));
        }
    }

    @EventHandler
    private void onGain(SwordXPGainEvent event) {
        final String TYPE = "Xp";
        final String NAMESPACE = "RIVAL_SWORDS";
        final double[] totalMulti = {0};

        BoosterFindResult pResult = IBoosterAPI.INSTANCE.getCache(event.getPlayer()).getBoosterDataManager().findActiveBooster(TYPE, NAMESPACE);
        if (pResult instanceof BoosterFindResult.Success boosterResult) {
            totalMulti[0] += boosterResult.getBoosterData().getMultiplier();
        }

        IBoosterAPI.INSTANCE.getGlobalBoosterManager().findGlobalBooster(TYPE, NAMESPACE, globalBooster -> {
            totalMulti[0] += globalBooster.getMultiplier();
            return null;
        }, () -> null);

        if (totalMulti[0] > 0) {
            event.setXP(calculateAmount(event.getXP(), totalMulti[0]));
        }
    }

    private double calculateAmount(double amount, double multi) {
        return amount * (multi <= 1 ? 1 + multi : multi);
    }
}
