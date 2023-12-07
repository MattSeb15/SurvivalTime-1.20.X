package net.most.survivaltimemod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.time.PlayerTimeData;

public class TimeHudOverlay {

    private static final ResourceLocation FILLED_TIME = new ResourceLocation(SurvivalTimeMod.MOD_ID,
            "textures/time/filled_time.png"); //60 mins 1h
    private static final ResourceLocation SUB_FILLED_TIME = new ResourceLocation(SurvivalTimeMod.MOD_ID,
            "textures/time/subfilled_time.png"); //45-59 mins

    private static final ResourceLocation HALF_TIME = new ResourceLocation(SurvivalTimeMod.MOD_ID,
            "textures/time/half_time.png"); //30-44 mins

    private static final ResourceLocation SUB_HALF_TIME = new ResourceLocation(SurvivalTimeMod.MOD_ID,
            "textures/time/subhalf_time.png"); //1-29 mins

    private static final ResourceLocation EMPTY_TIME = new ResourceLocation(SurvivalTimeMod.MOD_ID,
            "textures/time/empty_time.png"); //0 mins

    private static int pX(int x, int i) {
        return x - 94 + (i * 9);
    }

    ;

    private static int pY(int y) {
        return y - 39;
    }

    ;
    private static final float pUOffset = 0.0f;
    private static final float pVOffset = 0.0f;
    private static final int pWidth = 9;
    private static final int pHeight = 9;
    private static final int pTextureWidth = 9;
    private static final int pTextureHeight = 9;


    public static IGuiOverlay HUD_TIME = ((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {

        int x = screenWidth / 2;
        int y = screenHeight;

        if (!gui.getMinecraft().options.hideGui && gui.shouldDrawSurvivalElements()) {


            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//            for (int i = 0; i < 10; i++) {
//
//                guiGraphics.blit(FILLED_TIME, pX(x,i), pY(y), pUOffset, pVOffset, pWidth, pHeight, pTextureWidth,
//                pTextureHeight);
//
//            }
            LocalPlayer player = gui.getMinecraft().player;

            if (player != null) {
                PlayerTimeData playerTimeData = ClientPlayerTimeData.get();
                if(playerTimeData==null){
                    return;
                }
                float totalSeconds = playerTimeData.getTime();

                int fullHours = (int) (totalSeconds / 3600); // 3600 seconds = 1 hour

                int remainingSeconds = (int) (totalSeconds % 3600);
                //45-59 mins
                int subFullHours = remainingSeconds >= 2700.0f ? 1 : 0;
                //30-44 mins
                int halfHours = remainingSeconds >= 1800.0f ? 1 : 0;
                //1-29 mins
                int subHalfHours = remainingSeconds >= 600.0f ? 1 : 0;





                for (int i = 0; i < 10; i++) {

                    if(i<fullHours){
                        guiGraphics.blit(FILLED_TIME, pX(x,i), pY(y), pUOffset, pVOffset, pWidth, pHeight, pTextureWidth,
                                pTextureHeight);
                    }else if(i==fullHours){

                        if(subFullHours==1){
                            guiGraphics.blit(SUB_FILLED_TIME, pX(x,i), pY(y), pUOffset, pVOffset, pWidth, pHeight, pTextureWidth,
                                    pTextureHeight);
                        }else if(halfHours==1){
                            guiGraphics.blit(HALF_TIME, pX(x,i), pY(y), pUOffset, pVOffset, pWidth, pHeight, pTextureWidth,
                                    pTextureHeight);
                        }else if(subHalfHours==1){
                            guiGraphics.blit(SUB_HALF_TIME, pX(x,i), pY(y), pUOffset, pVOffset, pWidth, pHeight, pTextureWidth,
                                    pTextureHeight);
                        }else{
                            guiGraphics.blit(EMPTY_TIME, pX(x,i), pY(y), pUOffset, pVOffset, pWidth, pHeight, pTextureWidth,
                                    pTextureHeight);
                        }
                    }
                    else{
                        guiGraphics.blit(EMPTY_TIME, pX(x,i), pY(y), pUOffset, pVOffset, pWidth, pHeight, pTextureWidth,
                                pTextureHeight);
                    }
                }
            }


        }


    });


}
