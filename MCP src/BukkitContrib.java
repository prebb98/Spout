package net.minecraft.src;

import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import java.util.HashMap;
//BukkitContrib
public class BukkitContrib {
    private static int buildVersion = -1;
    private static int minorVersion = -1;
    private static int majorVersion = -1;
    private static int clientBuildVersion = 4;
    private static int clientMinorVersion = 0;
    private static int clientMajorVersion = 0;
    private static Minecraft game = null;
    public static HashMap<Integer, String> entityLabel = new HashMap<Integer, String>();
    
    public static void setVersion(String version) {
        try {
        
            String split[] = version.split("\\.");
            BukkitContrib.buildVersion = Integer.valueOf(split[2]);
            BukkitContrib.minorVersion = Integer.valueOf(split[1]);
            BukkitContrib.majorVersion = Integer.valueOf(split[0]);
        }
        catch (Exception e) {reset();}
        
        new Packet195KeyPress();
        new Packet196AirTime();
        new Packet197SkinURL();
        new Packet198EntityTitle();
    }
    
    public static String versionToString(String version) {
        String split[] = version.split("\\.");
        return ChatColor.getByCode(Integer.parseInt(split[0])).toString() + ChatColor.WHITE.toString() +
            ChatColor.getByCode(Integer.parseInt(split[1])) + ChatColor.WHITE.toString() + 
            ChatColor.getByCode(Integer.parseInt(split[2]));
    }
    
    public static String colorToString(String color) {
        String s = "";
        String split[] = color.split(ChatColor.WHITE.toString());
        for (int i = 0; i < split.length; i++) {
            int code = 0;
            for (int j = 0; j < split[i].length(); j++) {
                code += (int)(split[i].charAt(j));
            }
            s += (char)(code - ChatColor.BLACK.toString().charAt(0));
            if (i < color.length() -1) {
                s += ".";
            }
        }
        return s;
    }
    
    public static int getMajorVersion() {
        return BukkitContrib.majorVersion;
    }
    
    public static int getMinorVersion() {
        return BukkitContrib.minorVersion;
    }
    
    public static int getBuildVersion() {
        return BukkitContrib.buildVersion;
    }
    
    public static int getClientMajorVersion() {
        return BukkitContrib.clientMajorVersion;
    }
    
    public static int getClientMinorVersion() {
        return BukkitContrib.clientMinorVersion;
    }
    
    public static int getClientBuildVersion() {
        return BukkitContrib.clientBuildVersion;
    }
    
    public static String getClientVersionString() {
        return "" + BukkitContrib.clientMajorVersion + "." + BukkitContrib.clientMinorVersion + "." + BukkitContrib.clientBuildVersion;
    }
    
    public static String getVersionString() {
        return "" + BukkitContrib.majorVersion + "." + BukkitContrib.minorVersion + "." + BukkitContrib.buildVersion;
    }
    
    public static void resetBukkitContrib() {
        reset();
        game = null;
        entityLabel = new HashMap<Integer, String>();
    }
    
    private static void reset() {
        BukkitContrib.buildVersion = -1;
        BukkitContrib.minorVersion = -1;
        BukkitContrib.majorVersion = -1;
    }
    
    public static boolean isEnabled() {
        return getBuildVersion() > -1 && getMinorVersion() > -1 && getMajorVersion() > -1;
    }
    
    public static Minecraft getGameInstance() {
        if (game == null) {
            try {
                Field f = Minecraft.class.getDeclaredField("a"); //theMinecraft
                f.setAccessible(true);
                game = (Minecraft)f.get(null);
            }
            catch (Exception e) {game = null;}
        }
        return game;
    }

}