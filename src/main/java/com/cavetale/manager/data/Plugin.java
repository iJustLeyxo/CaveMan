package com.cavetale.manager.data;

import com.cavetale.manager.parser.InputException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

public enum Plugin implements PluginProvider {
    ADVICE_ANIMALS("AdviceAnimals", "com.winthier.adviceanimals", "adviceanimals", "0.1-SNAPSHOT", PluginCategory.DEPRECATED),
    AFK("AFK", "com.cavetale.afk", "afk", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    ANTI_POPUP("AntiPopup", "https://github.com/KaspianDev/AntiPopup/releases/download/5487999/AntiPopup-4.3.jar", PluginCategory.GLOBAL),
    AREA("Area", "com.cavetale.area", "area", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    ARMOR_STAND_EDITOR("ArmorStandEditor", "io.github.rypofalem.armorstandeditor", "armorstandeditor", "1.17-25", PluginCategory.GLOBAL),
    AUCTION("Auction", "com.cavetale.auction", "auction", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    BANS("Bans", "com.winthier.bans", "bans", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    BED_SPAWN("BedSpawn", "com.cavetale.bedspawn", "bedspawn", "0.1-SNAPSHOT", PluginCategory.DEPRECATED),
    BINGO("Bingo", "com.cavetale.bingo", "bingo", "0.1-SNAPSHOT", PluginCategory.MINI_GAME),
    BLOCK_CLIP("BlockClip", "com.cavetale.blockclip", "blockclip", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    BLOCK_TRIGGER("BlockTrigger", "com.cavetale.blocktrigger", "blocktrigger", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    BLUE_MAP("BlueMap", "https://github.com/BlueMap-Minecraft/BlueMap/releases/download/v1.7.2/BlueMap-1.7.2-spigot.jar", PluginCategory.DEPRECATED),
    BUYCRAFT_X("BuycraftX", "https://d2vpaemuugs53a.cloudfront.net/latest/minecraft-java/12.0.8/bukkit-1.13/BuycraftX.jar", PluginCategory.HUB),
    CAVES("Caves", "com.cavetale.caves", "caves", "0.1-SNAPSHOT", PluginCategory.WORLD_GEN),
    CHAIR("Chair", "com.cavetale.chair", "chair", "0.1-SNAPSHOT", PluginCategory.BUILD),
    CHAT("Chat", "com.winthier.chat", "chat", "0.1-SNAPSHOT", PluginCategory.CORE),
    CHRISTMAS("Christmas", "com.cavetale.christmas", "christmas", "0.1-SNAPSHOT", PluginCategory.DEPRECATED),
    COLORFALL("Colorfall", "io.github.feydk.colorfall", "colorfall", "0.1-SNAPSHOT", PluginCategory.MINI_GAME),
    CONNECT("Connect", "com.winthier.connect", "connect", "0.1-SNAPSHOT", PluginCategory.CORE),
    CORE("Core", "com.cavetale.core", "core", "0.1-SNAPSHOT", PluginCategory.CORE),
    COUNTDOWN("Countdown", "com.winthier.countdown", "countdown", "0.1", PluginCategory.GLOBAL),
    CRAFT_BAY("CraftBay", "com.winthier.craftbay", "craftbay", "2.26-SNAPSHOT", PluginCategory.DEPRECATED),
    CREATIVE("Creative", "com.winthier.creative", "creative", "0.1-SNAPSHOT", PluginCategory.CREATIVE),
    CULL_MOB("CullMob", "com.cavetale.cullmob", "cullmob", "0.1-SNAPSHOT", PluginCategory.HOME),
    DECORATOR("Decorator", "com.winthier.decorator", "decorator", "0.1-SNAPSHOT", PluginCategory.WORLD_GEN),
    DIRTY("Dirty", "com.cavetale.dirty", "dirty", "0.1-SNAPSHOT", PluginCategory.DEPRECATED),
    DUNGEONS("Dungeons", "com.cavetale.dungeons", "dungeons", "0.1-SNAPSHOT", PluginCategory.MINE),
    DUSK("Dusk", "com.winthier.dusk", "dusk", "0.1", PluginCategory.SURVIVAL),
    DUTIES("Duties", "me.th3pf.plugins", "duties", "0.1-SNAPSHOT", PluginCategory.DEPRECATED),
    DYNMAP("dynmap", "target/", "Dynmap-3.3-SNAPSHOT-spigot", PluginCategory.DEPRECATED),
    DYNMAP_HIDER("DynmapHider", (URI) null, PluginCategory.DEPRECATED),
    EASTER("Easter", "com.cavetale.easter", "easter", "0.1-SNAPSHOT", PluginCategory.SEASONAL),
    EDITOR("Editor", "com.cavetale.editor", "editor", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    ELECTION("Election", "com.cavetale.election", "election", "0.1-SNAPSHOT", PluginCategory.SURVIVAL),
    ENDERBALL("Enderball", "com.cavetale.enderball", "enderball", "0.1-SNAPSHOT", PluginCategory.MINI_GAME),
    ENEMY("Enemy", "com.cavetale.enemy", "enemy", "0.1-SNAPSHOT", PluginCategory.SURVIVAL),
    EXPLOITS("Exploits", "com.winthier.exploits", "exploits", "0.1-SNAPSHOT", PluginCategory.SURVIVAL),
    EXTREME_GRASS_GROWING("ExtremeGrassGrowing", "com.cavetale.extremegrassgrowing", "extremegrassgrowing", "0.1-SNAPSHOT", PluginCategory.HUB),
    FAM("Fam", "com.cavetale.fam", "fam", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    FAST_LEAF_DECAY("FastLeafDecay", "com.cavetale.fastleafdecay", "fastleafdecay", "1.0-SNAPSHOT", PluginCategory.SURVIVAL),
    FESTIVAL("Festival", "com.cavetale.festival", "festival", "0.1-SNAPSHOT", PluginCategory.SEASONAL),
    FLAT_GENERATOR("FlatGenerator", "com.cavetale.flatgenerator", "flatgenerator", "0.1-SNAPSHOT", PluginCategory.CREATIVE),
    FLY("Fly", "com.cavetale.fly", "fly", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    FREE_HAT("FreeHat", "com.cavetale.freehat", "freehat", "0.1-SNAPSHOT", PluginCategory.BUILD),
    GOLDEN_TICKET("GoldenTicket", "com.cavetale.goldenticket", "goldenticket", "0.1-SNAPSHOT", PluginCategory.SURVIVAL),
    HALLOWEEN("Halloween", "com.cavetale.halloween", "halloween", "0.1-SNAPSHOT", PluginCategory.DEPRECATED),
    HEAD_HUNTER("HeadHunter", "com.winthier.headhunter", "headhunter", "0.1-SNAPSHOT", PluginCategory.DEPRECATED),
    HEARTS("Hearts", "com.winthier.hearts", "hearts", "0.1-SNAPSHOT", PluginCategory.DEPRECATED),
    HIDE_AND_SEEK("HideAndSeek", "com.cavetale.hideandseek", "hideandseek", "0.1-SNAPSHOT", PluginCategory.MINI_GAME),
    HOME("Home", "com.cavetale.home", "home", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    HOPPER_FILTER("HopperFilter", "com.winthier.hopperfilter", "hopperfilter", "0.1-SNAPSHOT", PluginCategory.SURVIVAL),
    HOT_SWAP("HotSwap", "com.cavetale.hotswap", "hotswap", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    INVENTORY("Inventory", "com.cavetale.inventory", "inventory", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    INVISIBLE_ITEM_FRAMES("InvisibleItemFrames", "com.cavetale.invisibleitemframes", "invisibleitemframes", "0.1-SNAPSHOT", PluginCategory.DEPRECATED),
    ITEM_MERCHANT("ItemMerchant", "com.cavetale.itemmerchant", "itemmerchant", "0.1-SNAPSHOT", PluginCategory.DEPRECATED),
    ITEM_STORE("ItemStore", "com.winthier.itemstore", "itemstore", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    KEEP_INVENTORY("KeepInventory", "com.winthier.keepinventory", "keepinventory", "0.1-SNAPSHOT", PluginCategory.SURVIVAL),
    KING_OF_THE_LADDER("KingOfTheLadder", "com.cavetale.kotl", "kotl", "0.1-SNAPSHOT", PluginCategory.HUB),
    KIT("Kit", "com.winthier.kit", "kit", "0.1", PluginCategory.GLOBAL),
    LIBS_DISGUISES("LibsDisguises", "LibsDisguises", "LibsDisguises", "10.0.26-SNAPSHOT", PluginCategory.EVENT),
    LINK_PORTAL("LinkPortal", "com.winthier.linkportal", "linkportal", "0.1-SNAPSHOT", PluginCategory.DEPRECATED),
    MAGIC_MAP("MagicMap", "com.cavetale.magicmap", "magicmap", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    MAIL("Mail", "com.winthier.mail", "mail", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    MANUAL("Manual", "com.winthier.manual", "manual", "0.1-SNAPSHOT", PluginCategory.DEPRECATED),
    MAP_LOAD("MapLoad", "com.cavetale.mapload", "mapload", "0.1-SNAPSHOT", PluginCategory.UTIL),
    MASS_STORAGE("MassStorage", "com.cavetale.massstorage", "massstorage", "0.1-SNAPSHOT", PluginCategory.SURVIVAL),
    MAYPOLE("Maypole", "com.winthier.maypole", "maypole", "0.1", PluginCategory.SEASONAL),
    MEMBER_LIST("MemberList", "com.cavetale.memberlist", "memberlist", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    MENU("Menu", "com.cavetale.menu", "menu", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    MERCHANT("Merchant", "com.cavetale.merchant", "merchant", "0.1-SNAPSHOT", PluginCategory.BUILD),
    MIDI("Midi", "com.cavetale.midi", "midi", "0.1-SNAPSHOT", PluginCategory.UTIL),
    MINIVERSE("Miniverse", "com.cavetale.miniverse", "miniverse", "0.1-SNAPSHOT", PluginCategory.UTIL),
    MONEY("Money", "com.cavetale.money", "money", "0.1-SNAPSHOT", PluginCategory.CORE),
    MYTEMS("Mytems", "com.cavetale.mytems", "mytems", "0.1-SNAPSHOT", PluginCategory.CORE),
    NEO_PAINTING_SWITCH("neoPaintingSwitch", (URI) null, PluginCategory.DEPRECATED),
    NO_CHEAT_PLUS("NoCheatPlus", "fr.neatmonster", "nocheatplus-parent", "1.1-SNAPSHOT", PluginCategory.DEPRECATED),
    NU_VOTIFIER("NuVotifier", (URI) null, PluginCategory.HUB),
    OPEN_INV("OpenInv", "com.lishid", "openinvassembly", "4.1.6-SNAPSHOT", PluginCategory.GLOBAL),
    OVERBOARD("Overboard", "com.cavetale.overboard", "overboard", "0.1-SNAPSHOT", PluginCategory.MINI_GAME),
    PERM("Perm", "com.winthier.perm", "perm", "0.1-SNAPSHOT", PluginCategory.CORE),
    PHOTOS("Photos", "com.winthier.photos", "photos", "0.1-SNAPSHOT", PluginCategory.BUILD),
    PICTIONARY("Pictionary", "com.cavetale.pictionary", "pictionary", "0.1-SNAPSHOT", PluginCategory.CREATIVE),
    PING_TESTER("PingTester", "com.cavetale.pingtester", "pingtester", "0.1-SNAPSHOT", PluginCategory.DEPRECATED),
    PLAYER_CACHE("PlayerCache", "com.winthier.playercache", "playercache", "0.1-SNAPSHOT", PluginCategory.CORE),
    PLAYER_INFO("PlayerInfo", "com.winthier.playerinfo", "playerinfo", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    PLUG_INFO("PlugInfo", "com.cavetale.pluginfo", "pluginfo", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    PLUG_MAN("PlugMan", "com.rylinaux", "PlugMan", "2.1.7", PluginCategory.DEPRECATED),
    PLUG_MAN_X("PlugManX", "com.rylinaux", "PlugMan", "2.3.3", PluginCategory.GLOBAL),
    POCKET_MOB("PocketMob", "com.cavetale.pocketmob", "pocketmob", "0.1-SNAPSHOT", PluginCategory.SURVIVAL),
    POSTER("Poster", "com.cavetale.poster", "poster", "0.1-SNAPSHOT", PluginCategory.SURVIVAL),
    PROTECT("Protect", "com.winthier.protect", "protect", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    PROTOCOL_LIB("ProtocolLib", "com.comphenix.protocol", "ProtocolLib", "4.7.1-SNAPSHOT", PluginCategory.UTIL),
    PVPARENA("PVPArena", "com.cavetale.pvparena", "pvparena", "0.1-SNAPSHOT", PluginCategory.MINI_GAME),
    QUIZ("Quiz", "com.winthier.quiz", "quiz", "0.1-SNAPSHOT", PluginCategory.DEPRECATED),
    RACE("Race", "com.cavetale.race", "race", "0.1-SNAPSHOT", PluginCategory.MINI_GAME),
    RAID("Raid", "com.cavetale.raid", "raid", "0.1-SNAPSHOT", PluginCategory.MINI_GAME),
    RANDOM_PLAYER_HEAD("RandomPlayerHead", "com.winthier.rph", "random-player-head", "0.1-SNAPSHOT", PluginCategory.BUILD),
    REDSTONE_CLOCK_DETECTOR("RedstoneClockDetector", "me.hwei", "redstoneclockdetector", "0.2.8", PluginCategory.BUILD),
    RED_GREEN_LIGHT("RedGreenLight", "com.cavetale.redgreenlight", "redgreenlight", "0.1-SNAPSHOT", PluginCategory.HUB),
    RESIDENT("Resident", "com.cavetale.resident", "resident", "0.1-SNAPSHOT", PluginCategory.SURVIVAL),
    RESOURCE("Resource", "com.winthier.resource", "resource", "0.1", PluginCategory.SURVIVAL),
    RESOURCE_PACK("ResourcePack", "com.cavetale.resourcepack", "resourcepack", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    RULES("Rules", "com.winthier.rules", "rules", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    SERVER("Server", "com.cavetale.server", "server", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    SERVER_STATUS("ServerStatus", "com.cavetale.serverstatus", "serverstatus", "0.1-SNAPSHOT", PluginCategory.DEPRECATED),
    SHOP("Shop", "com.winthier.shop", "shop", "0.1-SNAPSHOT", PluginCategory.SURVIVAL),
    SHUTDOWN("Shutdown", "com.winthier.shutdown", "shutdown", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    SIDEBAR("Sidebar", "com.cavetale.sidebar", "sidebar", "0.1-SNAPSHOT", PluginCategory.CORE),
    SIGN_SPY("SignSpy", "com.cavetale.signspy", "signspy", "0.1-SNAPSHOT", PluginCategory.BUILD),
    SKILLS("Skills", "com.cavetale.skills", "skills", "0.1-SNAPSHOT", PluginCategory.SURVIVAL),
    SPAWN("Spawn", "com.winthier.spawn", "spawn", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    SPIKE("Spike", "com.cavetale.spike", "spike", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    SPLEEF("Spleef", "com.winthier.spleef", "spleef", "0.1-SNAPSHOT", PluginCategory.MINI_GAME),
    SQL("SQL", "com.winthier.sql", "sql", "0.1-SNAPSHOT", PluginCategory.CORE),
    STAR_BOOK("StarBook", "com.winthier.starbook", "starbook", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    STOP_RAIN("StopRain", "com.winthier.stoprain", "stoprain", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    STREAMER("Streamer", "com.cavetale.streamer", "streamer", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    STRUCTURE("Structure", "com.cavetale.structure", "structure", "0.1-SNAPSHOT", PluginCategory.SURVIVAL),
    SURVIVAL_GAMES("SurvivalGames", "com.cavetale.survivalgames", "survivalgames", "0.1-SNAPSHOT", PluginCategory.MINI_GAME),
    TELEVATOR("Televator", "com.cavetale.televator", "televator", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    TICKET("Ticket", "com.winthier.ticket", "ticket", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    TINFOIL("Tinfoil", "com.winthier.tinfoil", "tinfoil", "0.1", PluginCategory.GLOBAL),
    TITLE("Title", "com.winthier.title", "title", "0.1-SNAPSHOT", PluginCategory.CORE),
    TOO_MANY_ENTITIES("TooManyEntities", "com.winthier.toomanyentities", "toomanyentities", "0.1", PluginCategory.BUILD),
    TPA("TPA", "com.cavetale.tpa", "tpa", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    TREES("Trees", "com.cavetale.trees", "trees", "0.1-SNAPSHOT", PluginCategory.BUILD),
    TUTOR("Tutor", "com.cavetale.tutor", "tutor", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    VAULT("Vault", "net.milkbowl.vault", "Vault", "1.7", PluginCategory.CORE),
    VERTIGO("Vertigo", "io.github.feydk.vertigo", "vertigo", "0.1-SNAPSHOT", PluginCategory.MINI_GAME),
    VOID_GENERATOR("VoidGenerator", "com.cavetale.voidgenerator", "voidgenerator", "0.1-SNAPSHOT", PluginCategory.CORE),
    VOTE("Vote", "com.cavetale.vote", "vote", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    VULCAN("Vulcan", (URI) null, PluginCategory.SURVIVAL),
    WALL("Wall", "com.winthier.wall", "wall", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    WARDROBE("Wardrobe", "com.cavetale.wardrobe", "wardrobe", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    WARP("Warp", "com.cavetale.warp", "warp", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    WATCHDOG("Watchdog", "com.winthier.watchdog", "watchdog", "0.1-SNAPSHOT", PluginCategory.DEPRECATED),
    WATCHMAN("Watchman", "com.cavetale.watchman", "watchman", "0.1-SNAPSHOT", PluginCategory.BUILD),
    WIN_TAG("WinTag", "com.cavetale.wintag", "wintag", "0.1-SNAPSHOT", PluginCategory.BUILD),
    WORLDS("Worlds", "com.winthier.worlds", "worlds", "0.1-SNAPSHOT", PluginCategory.GLOBAL),
    WORLD_EDIT("WorldEdit", "worldedit-bukkit/build/libs/", "worldedit-bukkit-7.3.0-SNAPSHOT-dist", PluginCategory.GLOBAL),
    WORLD_GUARD("WorldGuard", "worldguard-bukkit/build/libs/", "worldguard-bukkit-7.0.6-SNAPSHOT-dist", PluginCategory.DEPRECATED),
    WORLD_MARKER("WorldMarker", "com.cavetale.worldmarker", "worldmarker", "0.1-SNAPSHOT", PluginCategory.CORE),
    XMAS("Xmas", "com.cavetale.xmas", "xmas", "0.1-SNAPSHOT", PluginCategory.SEASONAL);

    public final @NotNull String name;
    public final @Nullable URI uri;
    public final @NotNull PluginCategory[] categories;

    Plugin(@NotNull String name, @Nullable URI uri, @NotNull PluginCategory... categories) {
        this.name = name;
        this.uri = uri;
        this.categories = categories;
    }

    Plugin(@NotNull String name, @Nullable String uri, @NotNull PluginCategory... categories) {
        try {
            this.name = name;
            this.uri = new URI(uri);
            this.categories = categories;
        } catch (URISyntaxException e) {
            throw new URIError(uri, e);
        }
    }

    Plugin(@NotNull String name, @NotNull String groupId, @NotNull String artifactId, @NotNull String version, @NotNull PluginCategory... categories) {
        String uri = "https://cavetale.com/jenkins/job/" + name
                + "/lastSuccessfulBuild/" + groupId + "$" + artifactId
                + "/artifact/" + groupId + "/" + artifactId + "/" + version
                + "/" + artifactId + "-" + version + ".jar";
        try {
            this.name = name;
            this.uri = new URI(uri);
            this.categories = categories;
        } catch (URISyntaxException e) {
            throw new URIError(uri, e);
        }
    }

    Plugin(@NotNull String name, @NotNull String path, @NotNull String artifact, @NotNull PluginCategory... categories) {
        String uri = "https://cavetale.com/jenkins/job/" + name
                + "/lastSuccessfulBuild/artifact/" + artifact + ".jar";
        try {
            this.name = name;
            this.uri = new URI(uri);
            this.categories = categories;
        } catch (URISyntaxException e) {
            throw new URIError(uri, e);
        }
    }

    @Override
    public Set<Plugin> plugins() {
        return Set.of(this);
    }

    @Override
    public @NotNull String toString() {
        return this.name;
    }

    public static Plugin get(String ref) throws NotFoundException {
        for (Plugin p : Plugin.values()) {
            if (ref.equalsIgnoreCase(p.name)) return p;
        }
        throw new NotFoundException(ref);
    }

    public static class NotFoundException extends InputException {
        public NotFoundException(@NotNull String ref) {
            super("Plugin \"" + ref + "\" not found");
        }
    }

    public final class URIError extends DataError {
        public URIError(@NotNull String uri, @NotNull Throwable cause) {
            super("Faulty url \n" + uri + "\n in plugin " + name + ": " + cause.getMessage(), cause);
        }
    }
}