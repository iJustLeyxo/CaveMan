package com.cavetale.setup;

import lombok.Getter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Enums generated via script.
 */
@Getter
public enum Plugin implements PluginSet {
    ADVICE_ANIMALS("AdviceAnimals", "com.winthier.adviceanimals", "adviceanimals", "0.1-SNAPSHOT", Category.DEPRECATED),
    AFK("AFK", "com.cavetale.afk", "afk", "0.1-SNAPSHOT", Category.ALL),
    ANTI_POPUP("AntiPopup", url("https://github.com/KaspianDev/AntiPopup/releases/download/5487999/AntiPopup-4.3.jar"), Category.ALL),
    AREA("Area", "com.cavetale.area", "area", "0.1-SNAPSHOT", Category.ALL),
    ARMOR_STAND_EDITOR("ArmorStandEditor", "io.github.rypofalem.armorstandeditor", "armorstandeditor", "1.17-25", Category.ALL),
    AUCTION("Auction", "com.cavetale.auction", "auction", "0.1-SNAPSHOT", Category.ALL),
    BANS("Bans", "com.winthier.bans", "bans", "0.1-SNAPSHOT", Category.ALL),
    BED_SPAWN("BedSpawn", "com.cavetale.bedspawn", "bedspawn", "0.1-SNAPSHOT", Category.DEPRECATED),
    BINGO("Bingo", "com.cavetale.bingo", "bingo", "0.1-SNAPSHOT", Category.MINIGAME),
    BLOCK_CLIP("BlockClip", "com.cavetale.blockclip", "blockclip", "0.1-SNAPSHOT", Category.ALL),
    BLOCK_TRIGGER("BlockTrigger", "com.cavetale.blocktrigger", "blocktrigger", "0.1-SNAPSHOT", Category.ALL),
    BLUE_MAP("BlueMap", url("https://github.com/BlueMap-Minecraft/BlueMap/releases/download/v1.7.2/BlueMap-1.7.2-spigot.jar"), Category.DEPRECATED),
    BUYCRAFT_X("BuycraftX", url("https://d2vpaemuugs53a.cloudfront.net/latest/minecraft-java/12.0.8/bukkit-1.13/BuycraftX.jar"), Category.HUB),
    CAVES("Caves", "com.cavetale.caves", "caves", "0.1-SNAPSHOT", Category.WORLDGEN),
    CHAIR("Chair", "com.cavetale.chair", "chair", "0.1-SNAPSHOT", Category.BUILD),
    CHAT("Chat", "com.winthier.chat", "chat", "0.1-SNAPSHOT", Category.CORE),
    CHRISTMAS("Christmas", "com.cavetale.christmas", "christmas", "0.1-SNAPSHOT", Category.DEPRECATED),
    COLORFALL("Colorfall", "io.github.feydk.colorfall", "colorfall", "0.1-SNAPSHOT", Category.MINIGAME),
    CONNECT("Connect", "com.winthier.connect", "connect", "0.1-SNAPSHOT", Category.CORE),
    CORE("Core", "com.cavetale.core", "core", "0.1-SNAPSHOT", Category.CORE),
    COUNTDOWN("Countdown", "com.winthier.countdown", "countdown", "0.1", Category.ALL),
    CRAFT_BAY("CraftBay", "com.winthier.craftbay", "craftbay", "2.26-SNAPSHOT", Category.DEPRECATED),
    CREATIVE("Creative", "com.winthier.creative", "creative", "0.1-SNAPSHOT", Category.CREATIVE),
    CULL_MOB("CullMob", "com.cavetale.cullmob", "cullmob", "0.1-SNAPSHOT", Category.HOME),
    DECORATOR("Decorator", "com.winthier.decorator", "decorator", "0.1-SNAPSHOT", Category.WORLDGEN),
    DIRTY("Dirty", "com.cavetale.dirty", "dirty", "0.1-SNAPSHOT", Category.DEPRECATED),
    DUNGEONS("Dungeons", "com.cavetale.dungeons", "dungeons", "0.1-SNAPSHOT", Category.MINE),
    DUSK("Dusk", "com.winthier.dusk", "dusk", "0.1", Category.SURVIVAL),
    DUTIES("Duties", "me.th3pf.plugins", "duties", "0.1-SNAPSHOT", Category.DEPRECATED),
    DYNMAP("dynmap", "target/Dynmap-3.3-SNAPSHOT-spigot", Category.DEPRECATED),
    DYNMAP_HIDER("DynmapHider", (URL) null, Category.DEPRECATED),
    EASTER("Easter", "com.cavetale.easter", "easter", "0.1-SNAPSHOT", Category.SEASONAL),
    EDITOR("Editor", "com.cavetale.editor", "editor", "0.1-SNAPSHOT", Category.ALL),
    ELECTION("Election", "com.cavetale.election", "election", "0.1-SNAPSHOT", Category.SURVIVAL),
    ENDERBALL("Enderball", "com.cavetale.enderball", "enderball", "0.1-SNAPSHOT", Category.MINIGAME),
    ENEMY("Enemy", "com.cavetale.enemy", "enemy", "0.1-SNAPSHOT", Category.SURVIVAL),
    EXPLOITS("Exploits", "com.winthier.exploits", "exploits", "0.1-SNAPSHOT", Category.SURVIVAL),
    EXTREME_GRASS_GROWING("ExtremeGrassGrowing", "com.cavetale.extremegrassgrowing", "extremegrassgrowing", "0.1-SNAPSHOT", Category.HUB),
    FAM("Fam", "com.cavetale.fam", "fam", "0.1-SNAPSHOT", Category.ALL),
    FAST_LEAF_DECAY("FastLeafDecay", "com.cavetale.fastleafdecay", "fastleafdecay", "1.0-SNAPSHOT", Category.SURVIVAL),
    FESTIVAL("Festival", "com.cavetale.festival", "festival", "0.1-SNAPSHOT", Category.SEASONAL),
    FLAT_GENERATOR("FlatGenerator", "com.cavetale.flatgenerator", "flatgenerator", "0.1-SNAPSHOT", Category.CREATIVE),
    FLY("Fly", "com.cavetale.fly", "fly", "0.1-SNAPSHOT", Category.ALL),
    FREE_HAT("FreeHat", "com.cavetale.freehat", "freehat", "0.1-SNAPSHOT", Category.BUILD),
    GOLDEN_TICKET("GoldenTicket", "com.cavetale.goldenticket", "goldenticket", "0.1-SNAPSHOT", Category.SURVIVAL),
    HALLOWEEN("Halloween", "com.cavetale.halloween", "halloween", "0.1-SNAPSHOT", Category.DEPRECATED),
    HEAD_HUNTER("HeadHunter", "com.winthier.headhunter", "headhunter", "0.1-SNAPSHOT", Category.DEPRECATED),
    HEARTS("Hearts", "com.winthier.hearts", "hearts", "0.1-SNAPSHOT", Category.DEPRECATED),
    HIDE_AND_SEEK("HideAndSeek", "com.cavetale.hideandseek", "hideandseek", "0.1-SNAPSHOT", Category.MINIGAME),
    HOME("Home", "com.cavetale.home", "home", "0.1-SNAPSHOT", Category.ALL),
    HOPPER_FILTER("HopperFilter", "com.winthier.hopperfilter", "hopperfilter", "0.1-SNAPSHOT", Category.SURVIVAL),
    HOT_SWAP("HotSwap", "com.cavetale.hotswap", "hotswap", "0.1-SNAPSHOT", Category.ALL),
    INVENTORY("Inventory", "com.cavetale.inventory", "inventory", "0.1-SNAPSHOT", Category.ALL),
    INVISIBLE_ITEM_FRAMES("InvisibleItemFrames", "com.cavetale.invisibleitemframes", "invisibleitemframes", "0.1-SNAPSHOT", Category.DEPRECATED),
    ITEM_MERCHANT("ItemMerchant", "com.cavetale.itemmerchant", "itemmerchant", "0.1-SNAPSHOT", Category.DEPRECATED),
    ITEM_STORE("ItemStore", "com.winthier.itemstore", "itemstore", "0.1-SNAPSHOT", Category.ALL),
    KEEP_INVENTORY("KeepInventory", "com.winthier.keepinventory", "keepinventory", "0.1-SNAPSHOT", Category.SURVIVAL),
    KING_OF_THE_LADDER("KingOfTheLadder", "com.cavetale.kotl", "kotl", "0.1-SNAPSHOT", Category.HUB),
    KIT("Kit", "com.winthier.kit", "kit", "0.1", Category.ALL),
    LIBS_DISGUISES("LibsDisguises", "LibsDisguises", "LibsDisguises", "10.0.26-SNAPSHOT", Category.EVENT),
    LINK_PORTAL("LinkPortal", "com.winthier.linkportal", "linkportal", "0.1-SNAPSHOT", Category.DEPRECATED),
    MAGIC_MAP("MagicMap", "com.cavetale.magicmap", "magicmap", "0.1-SNAPSHOT", Category.ALL),
    MAIL("Mail", "com.winthier.mail", "mail", "0.1-SNAPSHOT", Category.ALL),
    MANUAL("Manual", "com.winthier.manual", "manual", "0.1-SNAPSHOT", Category.DEPRECATED),
    MAP_LOAD("MapLoad", "com.cavetale.mapload", "mapload", "0.1-SNAPSHOT", Category.UTIL),
    MASS_STORAGE("MassStorage", "com.cavetale.massstorage", "massstorage", "0.1-SNAPSHOT", Category.SURVIVAL),
    MAYPOLE("Maypole", "com.winthier.maypole", "maypole", "0.1", Category.SEASONAL),
    MEMBER_LIST("MemberList", "com.cavetale.memberlist", "memberlist", "0.1-SNAPSHOT", Category.ALL),
    MENU("Menu", "com.cavetale.menu", "menu", "0.1-SNAPSHOT", Category.ALL),
    MERCHANT("Merchant", "com.cavetale.merchant", "merchant", "0.1-SNAPSHOT", Category.BUILD),
    MIDI("Midi", "com.cavetale.midi", "midi", "0.1-SNAPSHOT", Category.UTIL),
    MINIVERSE("Miniverse", "com.cavetale.miniverse", "miniverse", "0.1-SNAPSHOT", Category.UTIL),
    MONEY("Money", "com.cavetale.money", "money", "0.1-SNAPSHOT", Category.CORE),
    MYTEMS("Mytems", "com.cavetale.mytems", "mytems", "0.1-SNAPSHOT", Category.CORE),
    NEO_PAINTING_SWITCH("neoPaintingSwitch", (URL) null, Category.DEPRECATED),
    NO_CHEAT_PLUS("NoCheatPlus", "fr.neatmonster", "nocheatplus-parent", "1.1-SNAPSHOT", Category.DEPRECATED),
    NU_VOTIFIER("NuVotifier", (URL) null, Category.HUB),
    OPEN_INV("OpenInv", "com.lishid", "openinvassembly", "4.1.6-SNAPSHOT", Category.ALL),
    OVERBOARD("Overboard", "com.cavetale.overboard", "overboard", "0.1-SNAPSHOT", Category.MINIGAME),
    PERM("Perm", "com.winthier.perm", "perm", "0.1-SNAPSHOT", Category.CORE),
    PHOTOS("Photos", "com.winthier.photos", "photos", "0.1-SNAPSHOT", Category.BUILD),
    PICTIONARY("Pictionary", "com.cavetale.pictionary", "pictionary", "0.1-SNAPSHOT", Category.CREATIVE),
    PING_TESTER("PingTester", "com.cavetale.pingtester", "pingtester", "0.1-SNAPSHOT", Category.DEPRECATED),
    PLAYER_CACHE("PlayerCache", "com.winthier.playercache", "playercache", "0.1-SNAPSHOT", Category.CORE),
    PLAYER_INFO("PlayerInfo", "com.winthier.playerinfo", "playerinfo", "0.1-SNAPSHOT", Category.ALL),
    PLUG_INFO("PlugInfo", "com.cavetale.pluginfo", "pluginfo", "0.1-SNAPSHOT", Category.ALL),
    PLUG_MAN("PlugMan", "com.rylinaux", "PlugMan", "2.1.7", Category.DEPRECATED),
    PLUG_MAN_X("PlugManX", "com.rylinaux", "PlugMan", "2.3.3", Category.ALL),
    POCKET_MOB("PocketMob", "com.cavetale.pocketmob", "pocketmob", "0.1-SNAPSHOT", Category.SURVIVAL),
    POSTER("Poster", "com.cavetale.poster", "poster", "0.1-SNAPSHOT", Category.SURVIVAL),
    PROTECT("Protect", "com.winthier.protect", "protect", "0.1-SNAPSHOT", Category.ALL),
    PROTOCOL_LIB("ProtocolLib", "com.comphenix.protocol", "ProtocolLib", "4.7.1-SNAPSHOT", Category.UTIL),
    PVPARENA("PVPArena", "com.cavetale.pvparena", "pvparena", "0.1-SNAPSHOT", Category.MINIGAME),
    QUIZ("Quiz", "com.winthier.quiz", "quiz", "0.1-SNAPSHOT", Category.DEPRECATED),
    RACE("Race", "com.cavetale.race", "race", "0.1-SNAPSHOT", Category.MINIGAME),
    RAID("Raid", "com.cavetale.raid", "raid", "0.1-SNAPSHOT", Category.MINIGAME),
    RANDOM_PLAYER_HEAD("RandomPlayerHead", "com.winthier.rph", "random-player-head", "0.1-SNAPSHOT", Category.BUILD),
    REDSTONE_CLOCK_DETECTOR("RedstoneClockDetector", "me.hwei", "redstoneclockdetector", "0.2.8", Category.BUILD),
    RED_GREEN_LIGHT("RedGreenLight", "com.cavetale.redgreenlight", "redgreenlight", "0.1-SNAPSHOT", Category.HUB),
    RESIDENT("Resident", "com.cavetale.resident", "resident", "0.1-SNAPSHOT", Category.SURVIVAL),
    RESOURCE("Resource", "com.winthier.resource", "resource", "0.1", Category.SURVIVAL),
    RESOURCE_PACK("ResourcePack", "com.cavetale.resourcepack", "resourcepack", "0.1-SNAPSHOT", Category.ALL),
    RULES("Rules", "com.winthier.rules", "rules", "0.1-SNAPSHOT", Category.ALL),
    SERVER("Server", "com.cavetale.server", "server", "0.1-SNAPSHOT", Category.ALL),
    SERVER_STATUS("ServerStatus", "com.cavetale.serverstatus", "serverstatus", "0.1-SNAPSHOT", Category.DEPRECATED),
    SHOP("Shop", "com.winthier.shop", "shop", "0.1-SNAPSHOT", Category.SURVIVAL),
    SHUTDOWN("Shutdown", "com.winthier.shutdown", "shutdown", "0.1-SNAPSHOT", Category.ALL),
    SIDEBAR("Sidebar", "com.cavetale.sidebar", "sidebar", "0.1-SNAPSHOT", Category.CORE),
    SIGN_SPY("SignSpy", "com.cavetale.signspy", "signspy", "0.1-SNAPSHOT", Category.BUILD),
    SKILLS("Skills", "com.cavetale.skills", "skills", "0.1-SNAPSHOT", Category.SURVIVAL),
    SPAWN("Spawn", "com.winthier.spawn", "spawn", "0.1-SNAPSHOT", Category.ALL),
    SPIKE("Spike", "com.cavetale.spike", "spike", "0.1-SNAPSHOT", Category.ALL),
    SPLEEF("Spleef", "com.winthier.spleef", "spleef", "0.1-SNAPSHOT", Category.MINIGAME),
    SQL("SQL", "com.winthier.sql", "sql", "0.1-SNAPSHOT", Category.CORE),
    STAR_BOOK("StarBook", "com.winthier.starbook", "starbook", "0.1-SNAPSHOT", Category.ALL),
    STOP_RAIN("StopRain", "com.winthier.stoprain", "stoprain", "0.1-SNAPSHOT", Category.ALL),
    STREAMER("Streamer", "com.cavetale.streamer", "streamer", "0.1-SNAPSHOT", Category.ALL),
    STRUCTURE("Structure", "com.cavetale.structure", "structure", "0.1-SNAPSHOT", Category.SURVIVAL),
    SURVIVAL_GAMES("SurvivalGames", "com.cavetale.survivalgames", "survivalgames", "0.1-SNAPSHOT", Category.MINIGAME),
    TELEVATOR("Televator", "com.cavetale.televator", "televator", "0.1-SNAPSHOT", Category.ALL),
    TICKET("Ticket", "com.winthier.ticket", "ticket", "0.1-SNAPSHOT", Category.ALL),
    TINFOIL("Tinfoil", "com.winthier.tinfoil", "tinfoil", "0.1", Category.ALL),
    TITLE("Title", "com.winthier.title", "title", "0.1-SNAPSHOT", Category.CORE),
    TOO_MANY_ENTITIES("TooManyEntities", "com.winthier.toomanyentities", "toomanyentities", "0.1", Category.BUILD),
    TPA("TPA", "com.cavetale.tpa", "tpa", "0.1-SNAPSHOT", Category.ALL),
    TREES("Trees", "com.cavetale.trees", "trees", "0.1-SNAPSHOT", Category.BUILD),
    TUTOR("Tutor", "com.cavetale.tutor", "tutor", "0.1-SNAPSHOT", Category.ALL),
    VAULT("Vault", "net.milkbowl.vault", "Vault", "1.7", Category.CORE),
    VERTIGO("Vertigo", "io.github.feydk.vertigo", "vertigo", "0.1-SNAPSHOT", Category.MINIGAME),
    VOID_GENERATOR("VoidGenerator", "com.cavetale.voidgenerator", "voidgenerator", "0.1-SNAPSHOT", Category.CORE),
    VOTE("Vote", "com.cavetale.vote", "vote", "0.1-SNAPSHOT", Category.ALL),
    VULCAN("Vulcan", (URL) null, Category.SURVIVAL),
    WALL("Wall", "com.winthier.wall", "wall", "0.1-SNAPSHOT", Category.ALL),
    WARDROBE("Wardrobe", "com.cavetale.wardrobe", "wardrobe", "0.1-SNAPSHOT", Category.ALL),
    WARP("Warp", "com.cavetale.warp", "warp", "0.1-SNAPSHOT", Category.ALL),
    WATCHDOG("Watchdog", "com.winthier.watchdog", "watchdog", "0.1-SNAPSHOT", Category.DEPRECATED),
    WATCHMAN("Watchman", "com.cavetale.watchman", "watchman", "0.1-SNAPSHOT", Category.BUILD),
    WIN_TAG("WinTag", "com.cavetale.wintag", "wintag", "0.1-SNAPSHOT", Category.BUILD),
    WORLDS("Worlds", "com.winthier.worlds", "worlds", "0.1-SNAPSHOT", Category.ALL),
    WORLD_EDIT("WorldEdit", "worldedit-bukkit/build/libs/worldedit-bukkit-7.3.0-SNAPSHOT-dist", Category.ALL),
    WORLD_GUARD("WorldGuard", "worldguard-bukkit/build/libs/worldguard-bukkit-7.0.6-SNAPSHOT-dist", Category.DEPRECATED),
    WORLD_MARKER("WorldMarker", "com.cavetale.worldmarker", "worldmarker", "0.1-SNAPSHOT", Category.CORE),
    XMAS("Xmas", "com.cavetale.xmas", "xmas", "0.1-SNAPSHOT", Category.SEASONAL),
    ;

    public final String name;
    public final URL downloadUrl;
    public final Category category;

    private static URL url(String path) {
        try {
            return new URL(path);
        } catch (MalformedURLException murle) {
            throw new IllegalStateException(murle);
        }
    }

    Plugin(final String name, final URL url, final Category category) {
        this.name = name;
        this.downloadUrl = url;
        this.category = category;
    }

    Plugin(final String name, final String groupId, final String artifactId, final String version, final Category category) {
        this.name = name;
        this.downloadUrl = url("https://cavetale.com/jenkins/job/" + name
                               + "/lastSuccessfulBuild/" + groupId + "$" + artifactId
                               + "/artifact/" + groupId + "/" + artifactId + "/" + version
                               + "/" + artifactId + "-" + version + ".jar");
        this.category = category;
    }

    Plugin(final String name, final String artifactPath, final Category category) {
        this.name = name;
        this.downloadUrl = url("https://cavetale.com/jenkins/job/" + name
                               + "/lastSuccessfulBuild/artifact/" + artifactPath + ".jar");
        this.category = category;
    }

    @Override
    public Set<Plugin> getPlugins() {
        return Set.of(this);
    }

    public static Plugin of(String name) {
        for (Plugin plugin : Plugin.values()) {
            if (name.equalsIgnoreCase(plugin.name)) return plugin;
        }
        return null;
    }

    public static String toString(Collection<Plugin> input) {
        ArrayList<String> list = new ArrayList<>(input.size());
        for (Plugin plugin : input) {
            list.add(plugin.name);
        }
        Collections.sort(list);
        return String.join(" ", list);
    }
}
