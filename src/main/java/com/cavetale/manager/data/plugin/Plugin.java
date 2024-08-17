package com.cavetale.manager.data.plugin;

import com.cavetale.manager.data.DataError;
import com.cavetale.manager.parser.InputException;
import com.cavetale.manager.util.Download;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Set;

/**
 * List of available plugins
 */
public enum Plugin implements Provider {
    ADVICE_ANIMALS("AdviceAnimals", "com.winthier.adviceanimals", "adviceanimals", "0.1-SNAPSHOT", Category.DEPRECATED),
    AFK("AFK", "com.cavetale.afk", "afk", "0.1-SNAPSHOT", Category.GLOBAL),
    ANTI_POPUP("AntiPopup", "https://github.com/KaspianDev/AntiPopup/releases/download/b7a08d9/AntiPopup-9.2.jar", Category.GLOBAL),
    AREA("Area", "com.cavetale.area", "area", "0.1-SNAPSHOT", Category.GLOBAL),
    ARMOR_STAND_EDITOR("ArmorStandEditor", "io.github.rypofalem.armorstandeditor", "armorstandeditor", "1.17-25", Category.GLOBAL),
    AUCTION("Auction", "com.cavetale.auction", "auction", "0.1-SNAPSHOT", Category.GLOBAL),
    BANS("Bans", "com.winthier.bans", "bans", "0.1-SNAPSHOT", Category.GLOBAL),
    BED_SPAWN("BedSpawn", "com.cavetale.bedspawn", "bedspawn", "0.1-SNAPSHOT", Category.DEPRECATED),
    BINGO("Bingo", "com.cavetale.bingo", "bingo", "0.1-SNAPSHOT", Category.MINI_GAME),
    BLOCK_CLIP("BlockClip", "com.cavetale.blockclip", "blockclip", "0.1-SNAPSHOT", Category.GLOBAL),
    BLOCK_TRIGGER("BlockTrigger", "com.cavetale.blocktrigger", "blocktrigger", "0.1-SNAPSHOT", Category.GLOBAL),
    BLUE_MAP("BlueMap", "https://github.com/BlueMap-Minecraft/BlueMap/releases/download/v1.7.2/BlueMap-1.7.2-spigot.jar", Category.DEPRECATED),
    BUYCRAFT_X("BuycraftX", "https://d2vpaemuugs53a.cloudfront.net/latest/minecraft-java/12.0.8/bukkit-1.13/BuycraftX.jar", Category.HUB),
    CAVES("Caves", "com.cavetale.caves", "caves", "0.1-SNAPSHOT", Category.WORLD_GEN),
    CHAIR("Chair", "com.cavetale.chair", "chair", "0.1-SNAPSHOT", Category.BUILD),
    CHAT("Chat", "com.winthier.chat", "chat", "0.1-SNAPSHOT", Category.CORE),
    CHRISTMAS("Christmas", "com.cavetale.christmas", "christmas", "0.1-SNAPSHOT", Category.DEPRECATED),
    COLORFALL("Colorfall", "io.github.feydk.colorfall", "colorfall", "0.1-SNAPSHOT", Category.MINI_GAME),
    CONNECT("Connect", "com.winthier.connect", "connect", "0.1-SNAPSHOT", Category.CORE),
    CORE("Core", "com.cavetale.core", "core", "0.1-SNAPSHOT", Category.CORE),
    COUNTDOWN("Countdown", "com.winthier.countdown", "countdown", "0.1", Category.GLOBAL),
    CRAFT_BAY("CraftBay", "com.winthier.craftbay", "craftbay", "2.26-SNAPSHOT", Category.DEPRECATED),
    CREATIVE("Creative", "com.winthier.creative", "creative", "0.1-SNAPSHOT", Category.CREATIVE),
    CULL_MOB("CullMob", "com.cavetale.cullmob", "cullmob", "0.1-SNAPSHOT", Category.HOME),
    DECORATOR("Decorator", "com.winthier.decorator", "decorator", "0.1-SNAPSHOT", Category.WORLD_GEN),
    DIRTY("Dirty", "com.cavetale.dirty", "dirty", "0.1-SNAPSHOT", Category.DEPRECATED),
    DUNGEONS("Dungeons", "com.cavetale.dungeons", "dungeons", "0.1-SNAPSHOT", Category.MINE),
    DUSK("Dusk", "com.winthier.dusk", "dusk", "0.1", Category.SURVIVAL),
    DUTIES("Duties", "me.th3pf.plugins", "duties", "0.1-SNAPSHOT", Category.DEPRECATED),
    DYNMAP("dynmap", "target/", "Dynmap-3.3-SNAPSHOT-spigot", Category.DEPRECATED),
    DYNMAP_HIDER("DynmapHider", (URI) null, Category.DEPRECATED),
    EASTER("Easter", "com.cavetale.easter", "easter", "0.1-SNAPSHOT", Category.SEASONAL),
    EDITOR("Editor", "com.cavetale.editor", "editor", "0.1-SNAPSHOT", Category.GLOBAL),
    ELECTION("Election", "com.cavetale.election", "election", "0.1-SNAPSHOT", Category.SURVIVAL),
    ENDERBALL("Enderball", "com.cavetale.enderball", "enderball", "0.1-SNAPSHOT", Category.MINI_GAME),
    ENEMY("Enemy", "com.cavetale.enemy", "enemy", "0.1-SNAPSHOT", Category.SURVIVAL),
    EXPLOITS("Exploits", "com.winthier.exploits", "exploits", "0.1-SNAPSHOT", Category.SURVIVAL),
    EXTREME_GRASS_GROWING("ExtremeGrassGrowing", "com.cavetale.extremegrassgrowing", "extremegrassgrowing", "0.1-SNAPSHOT", Category.HUB),
    FAM("Fam", "com.cavetale.fam", "fam", "0.1-SNAPSHOT", Category.GLOBAL),
    FAST_LEAF_DECAY("FastLeafDecay", "com.cavetale.fastleafdecay", "fastleafdecay", "1.0-SNAPSHOT", Category.SURVIVAL),
    FESTIVAL("Festival", "com.cavetale.festival", "festival", "0.1-SNAPSHOT", Category.SEASONAL),
    FLAT_GENERATOR("FlatGenerator", "com.cavetale.flatgenerator", "flatgenerator", "0.1-SNAPSHOT", Category.CREATIVE),
    FLY("Fly", "com.cavetale.fly", "fly", "0.1-SNAPSHOT", Category.GLOBAL),
    FREE_HAT("FreeHat", "com.cavetale.freehat", "freehat", "0.1-SNAPSHOT", Category.BUILD),
    GOLDEN_TICKET("GoldenTicket", "com.cavetale.goldenticket", "goldenticket", "0.1-SNAPSHOT", Category.SURVIVAL),
    HALLOWEEN("Halloween", "com.cavetale.halloween", "halloween", "0.1-SNAPSHOT", Category.DEPRECATED),
    HEAD_HUNTER("HeadHunter", "com.winthier.headhunter", "headhunter", "0.1-SNAPSHOT", Category.DEPRECATED),
    HEARTS("Hearts", "com.winthier.hearts", "hearts", "0.1-SNAPSHOT", Category.DEPRECATED),
    HIDE_AND_SEEK("HideAndSeek", "com.cavetale.hideandseek", "hideandseek", "0.1-SNAPSHOT", Category.MINI_GAME),
    HOME("Home", "com.cavetale.home", "home", "0.1-SNAPSHOT", Category.GLOBAL),
    HOPPER_FILTER("HopperFilter", "com.winthier.hopperfilter", "hopperfilter", "0.1-SNAPSHOT", Category.SURVIVAL),
    HOT_SWAP("HotSwap", "com.cavetale.hotswap", "hotswap", "0.1-SNAPSHOT", Category.GLOBAL),
    INVENTORY("Inventory", "com.cavetale.inventory", "inventory", "0.1-SNAPSHOT", Category.GLOBAL),
    INVISIBLE_ITEM_FRAMES("InvisibleItemFrames", "com.cavetale.invisibleitemframes", "invisibleitemframes", "0.1-SNAPSHOT", Category.DEPRECATED),
    ITEM_MERCHANT("ItemMerchant", "com.cavetale.itemmerchant", "itemmerchant", "0.1-SNAPSHOT", Category.DEPRECATED),
    ITEM_STORE("ItemStore", "com.winthier.itemstore", "itemstore", "0.1-SNAPSHOT", Category.GLOBAL),
    KEEP_INVENTORY("KeepInventory", "com.winthier.keepinventory", "keepinventory", "0.1-SNAPSHOT", Category.SURVIVAL),
    KING_OF_THE_LADDER("KingOfTheLadder", "com.cavetale.kotl", "kotl", "0.1-SNAPSHOT", Category.HUB),
    KIT("Kit", "com.winthier.kit", "kit", "0.1", Category.GLOBAL),
    LIBS_DISGUISES("LibsDisguises", "LibsDisguises", "LibsDisguises", "10.0.26-SNAPSHOT", Category.EVENT),
    LINK_PORTAL("LinkPortal", "com.winthier.linkportal", "linkportal", "0.1-SNAPSHOT", Category.DEPRECATED),
    MAGIC_MAP("MagicMap", "com.cavetale.magicmap", "magicmap", "0.1-SNAPSHOT", Category.GLOBAL),
    MAIL("Mail", "com.winthier.mail", "mail", "0.1-SNAPSHOT", Category.GLOBAL),
    MANUAL("Manual", "com.winthier.manual", "manual", "0.1-SNAPSHOT", Category.DEPRECATED),
    MAP_LOAD("MapLoad", "com.cavetale.mapload", "mapload", "0.1-SNAPSHOT", Category.UTIL),
    MASS_STORAGE("MassStorage", "com.cavetale.massstorage", "massstorage", "0.1-SNAPSHOT", Category.SURVIVAL),
    MAYPOLE("Maypole", "com.winthier.maypole", "maypole", "0.1", Category.SEASONAL),
    MEMBER_LIST("MemberList", "com.cavetale.memberlist", "memberlist", "0.1-SNAPSHOT", Category.GLOBAL),
    MENU("Menu", "com.cavetale.menu", "menu", "0.1-SNAPSHOT", Category.GLOBAL),
    MERCHANT("Merchant", "com.cavetale.merchant", "merchant", "0.1-SNAPSHOT", Category.BUILD),
    MIDI("Midi", "com.cavetale.midi", "midi", "0.1-SNAPSHOT", Category.UTIL),
    MINIVERSE("Miniverse", "com.cavetale.miniverse", "miniverse", "0.1-SNAPSHOT", Category.UTIL),
    MONEY("Money", "com.cavetale.money", "money", "0.1-SNAPSHOT", Category.CORE),
    MYTEMS("Mytems", "com.cavetale.mytems", "mytems", "0.1-SNAPSHOT", Category.CORE),
    NEO_PAINTING_SWITCH("neoPaintingSwitch", (URI) null, Category.DEPRECATED),
    NO_CHEAT_PLUS("NoCheatPlus", "fr.neatmonster", "nocheatplus-parent", "1.1-SNAPSHOT", Category.DEPRECATED),
    NU_VOTIFIER("NuVotifier", (URI) null, Category.HUB),
    OPEN_INV("OpenInv", "https://github.com/Jikoo/OpenInv/releases/download/5.1.1/OpenInv.jar", Category.GLOBAL),
    OVERBOARD("Overboard", "com.cavetale.overboard", "overboard", "0.1-SNAPSHOT", Category.MINI_GAME),
    PERM("Perm", "com.winthier.perm", "perm", "0.1-SNAPSHOT", Category.CORE),
    PHOTOS("Photos", "com.winthier.photos", "photos", "0.1-SNAPSHOT", Category.BUILD),
    PICTIONARY("Pictionary", "com.cavetale.pictionary", "pictionary", "0.1-SNAPSHOT", Category.CREATIVE),
    PING_TESTER("PingTester", "com.cavetale.pingtester", "pingtester", "0.1-SNAPSHOT", Category.DEPRECATED),
    PLAYER_CACHE("PlayerCache", "com.winthier.playercache", "playercache", "0.1-SNAPSHOT", Category.CORE),
    PLAYER_INFO("PlayerInfo", "com.winthier.playerinfo", "playerinfo", "0.1-SNAPSHOT", Category.GLOBAL),
    PLUG_INFO("PlugInfo", "com.cavetale.pluginfo", "pluginfo", "0.1-SNAPSHOT", Category.GLOBAL),
    PLUG_MAN("PlugMan", "com.rylinaux", "PlugMan", "2.1.7", Category.DEPRECATED),
    PLUG_MAN_X("PlugManX", "com.rylinaux", "PlugMan", "2.3.3", Category.GLOBAL),
    POCKET_MOB("PocketMob", "com.cavetale.pocketmob", "pocketmob", "0.1-SNAPSHOT", Category.SURVIVAL),
    POSTER("Poster", "com.cavetale.poster", "poster", "0.1-SNAPSHOT", Category.SURVIVAL),
    PROTECT("Protect", "com.winthier.protect", "protect", "0.1-SNAPSHOT", Category.GLOBAL),
    PROTOCOL_LIB("ProtocolLib", "com.comphenix.protocol", "ProtocolLib", "4.7.1-SNAPSHOT", Category.UTIL),
    PVPARENA("PVPArena", "com.cavetale.pvparena", "pvparena", "0.1-SNAPSHOT", Category.MINI_GAME),
    QUIZ("Quiz", "com.winthier.quiz", "quiz", "0.1-SNAPSHOT", Category.DEPRECATED),
    RACE("Race", "com.cavetale.race", "race", "0.1-SNAPSHOT", Category.MINI_GAME),
    RAID("Raid", "com.cavetale.raid", "raid", "0.1-SNAPSHOT", Category.MINI_GAME),
    RANDOM_PLAYER_HEAD("RandomPlayerHead", "com.winthier.rph", "random-player-head", "0.1-SNAPSHOT", Category.BUILD),
    REDSTONE_CLOCK_DETECTOR("RedstoneClockDetector", "me.hwei", "redstoneclockdetector", "0.2.8", Category.BUILD),
    RED_GREEN_LIGHT("RedGreenLight", "com.cavetale.redgreenlight", "redgreenlight", "0.1-SNAPSHOT", Category.HUB),
    RESIDENT("Resident", "com.cavetale.resident", "resident", "0.1-SNAPSHOT", Category.SURVIVAL),
    RESOURCE("Resource", "com.winthier.resource", "resource", "0.1", Category.SURVIVAL),
    RESOURCE_PACK("ResourcePack", "com.cavetale.resourcepack", "resourcepack", "0.1-SNAPSHOT", Category.GLOBAL),
    RULES("Rules", "com.winthier.rules", "rules", "0.1-SNAPSHOT", Category.GLOBAL),
    SERVER("Server", "com.cavetale.server", "server", "0.1-SNAPSHOT", Category.GLOBAL),
    SERVER_STATUS("ServerStatus", "com.cavetale.serverstatus", "serverstatus", "0.1-SNAPSHOT", Category.DEPRECATED),
    SHOP("Shop", "com.winthier.shop", "shop", "0.1-SNAPSHOT", Category.SURVIVAL),
    SHUTDOWN("Shutdown", "com.winthier.shutdown", "shutdown", "0.1-SNAPSHOT", Category.GLOBAL),
    SIDEBAR("Sidebar", "com.cavetale.sidebar", "sidebar", "0.1-SNAPSHOT", Category.CORE),
    SIGN_SPY("SignSpy", "com.cavetale.signspy", "signspy", "0.1-SNAPSHOT", Category.BUILD),
    SKILLS("Skills", "com.cavetale.skills", "skills", "0.1-SNAPSHOT", Category.SURVIVAL),
    SPAWN("Spawn", "com.winthier.spawn", "spawn", "0.1-SNAPSHOT", Category.GLOBAL),
    SPIKE("Spike", "com.cavetale.spike", "spike", "0.1-SNAPSHOT", Category.GLOBAL),
    SPLEEF("Spleef", "com.winthier.spleef", "spleef", "0.1-SNAPSHOT", Category.MINI_GAME),
    SQL("SQL", "com.winthier.sql", "sql", "0.1-SNAPSHOT", Category.CORE),
    STAR_BOOK("StarBook", "com.winthier.starbook", "starbook", "0.1-SNAPSHOT", Category.GLOBAL),
    STOP_RAIN("StopRain", "com.winthier.stoprain", "stoprain", "0.1-SNAPSHOT", Category.GLOBAL),
    STREAMER("Streamer", "com.cavetale.streamer", "streamer", "0.1-SNAPSHOT", Category.GLOBAL),
    STRUCTURE("Structure", "com.cavetale.structure", "structure", "0.1-SNAPSHOT", Category.SURVIVAL),
    SURVIVAL_GAMES("SurvivalGames", "com.cavetale.survivalgames", "survivalgames", "0.1-SNAPSHOT", Category.MINI_GAME),
    TELEVATOR("Televator", "com.cavetale.televator", "televator", "0.1-SNAPSHOT", Category.GLOBAL),
    TICKET("Ticket", "com.winthier.ticket", "ticket", "0.1-SNAPSHOT", Category.GLOBAL),
    TINFOIL("Tinfoil", "com.winthier.tinfoil", "tinfoil", "0.1", Category.GLOBAL),
    TITLE("Title", "com.winthier.title", "title", "0.1-SNAPSHOT", Category.CORE),
    TOO_MANY_ENTITIES("TooManyEntities", "com.winthier.toomanyentities", "toomanyentities", "0.1", Category.BUILD),
    TPA("TPA", "com.cavetale.tpa", "tpa", "0.1-SNAPSHOT", Category.GLOBAL),
    TREES("Trees", "com.cavetale.trees", "trees", "0.1-SNAPSHOT", Category.BUILD),
    TUTOR("Tutor", "com.cavetale.tutor", "tutor", "0.1-SNAPSHOT", Category.GLOBAL),
    VAULT("Vault", "net.milkbowl.vault", "Vault", "1.7", Category.CORE),
    VERTIGO("Vertigo", "io.github.feydk.vertigo", "vertigo", "0.1-SNAPSHOT", Category.MINI_GAME),
    VOID_GENERATOR("VoidGenerator", "com.cavetale.voidgenerator", "voidgenerator", "0.1-SNAPSHOT", Category.CORE),
    VOTE("Vote", "com.cavetale.vote", "vote", "0.1-SNAPSHOT", Category.GLOBAL),
    VULCAN("Vulcan", (URI) null, Category.SURVIVAL),
    WALL("Wall", "com.winthier.wall", "wall", "0.1-SNAPSHOT", Category.GLOBAL),
    WARDROBE("Wardrobe", "com.cavetale.wardrobe", "wardrobe", "0.1-SNAPSHOT", Category.GLOBAL),
    WARP("Warp", "com.cavetale.warp", "warp", "0.1-SNAPSHOT", Category.GLOBAL),
    WATCHDOG("Watchdog", "com.winthier.watchdog", "watchdog", "0.1-SNAPSHOT", Category.DEPRECATED),
    WATCHMAN("Watchman", "com.cavetale.watchman", "watchman", "0.1-SNAPSHOT", Category.BUILD),
    WIN_TAG("WinTag", "com.cavetale.wintag", "wintag", "0.1-SNAPSHOT", Category.BUILD),
    WORLDS("Worlds", "com.winthier.worlds", "worlds", "0.1-SNAPSHOT", Category.GLOBAL),
    WORLD_EDIT("WorldEdit", "https://dev.bukkit.org/projects/worldedit/files/5613179/download", Category.GLOBAL),
    WORLD_GUARD("WorldGuard", "worldguard-bukkit/build/libs/", "worldguard-bukkit-7.0.6-SNAPSHOT-dist", Category.DEPRECATED),
    WORLD_MARKER("WorldMarker", "com.cavetale.worldmarker", "worldmarker", "0.1-SNAPSHOT", Category.CORE),
    XMAS("Xmas", "com.cavetale.xmas", "xmas", "0.1-SNAPSHOT", Category.SEASONAL);

    public final @NotNull String ref;
    public final @Nullable URI uri;
    public final @NotNull Category[] categories;

    Plugin(@NotNull String ref, @Nullable URI uri, @NotNull Category... categories) {
        this.ref = ref;
        this.uri = uri;
        this.categories = categories;
    }

    Plugin(@NotNull String ref, @Nullable String uri, @NotNull Category... categories) {
        try {
            this.ref = ref;
            if (uri != null) {
                this.uri = new URI(uri);
            } else {
                this.uri = null;
            }
            this.categories = categories;
        } catch (URISyntaxException e) {
            throw new URIError(uri, e);
        }
    }

    Plugin(@NotNull String ref, @NotNull String groupId, @NotNull String artifactId,
           @NotNull String version, @NotNull Category... categories) {
        String uri = "https://cavetale.com/jenkins/job/" + ref + "/lastSuccessfulBuild/" + groupId +
                "$" + artifactId + "/artifact/" + groupId + "/" + artifactId + "/" + version + "/" +
                artifactId + "-" + version + ".jar";
        try {
            this.ref = ref;
            this.uri = new URI(uri);
            this.categories = categories;
        } catch (URISyntaxException e) {
            throw new URIError(uri, e);
        }
    }

    Plugin(@NotNull String ref, @NotNull String path, @NotNull String artifact,
           @NotNull Category... categories) {
        String uri = "https://cavetale.com/jenkins/job/" + ref + "/lastSuccessfulBuild/artifact/" +
                path + artifact + ".jar";
        try {
            this.ref = ref;
            this.uri = new URI(uri);
            this.categories = categories;
        } catch (URISyntaxException e) {
            throw new URIError(uri, e);
        }
    }

    public void install() { // TODO :Folder creation
        Console.log(Type.INFO, "Installing " + this.ref);
        File file = new File("plugins/", this.ref + ".jar");
        if (file.exists()) {
            if (!Console.log(Type.INFO, Style.WARN, " skipped (already installed)\n")) {
                Console.log(Type.WARN, "Installing " + this.ref + " skipped (already installed)\n");
            }
            return;
        }
        if (this.uri == null) { // TODO: Get rid of no uri plugins
            if (!Console.log(Type.INFO, Style.WARN, " skipped (no uri)\n")) {
                Console.log(Type.WARN, "Installing " + this.ref + " skipped (no uri)\n");
            }
            return;
        }
        try {
            Download.download(this.uri, file);
            Console.log(Type.INFO, Style.DONE, " done\n");
        } catch (IOException e) {
            if (!Console.log(Type.INFO, Style.ERR, " failed\n")) {
                Console.log(Type.ERR, "Installing " + this.ref + " failed\n");
            }
        }
    }

    public void link(@NotNull String path) {
        Console.log(Type.INFO, "Linking " + this.ref);
        File link = new File(new File("plugins"), this.ref + ".jar");
        File original = new File(new File(path), this.ref + ".jar");
        if (link.exists()) {
            if (!Console.log(Type.INFO, Style.WARN, " skipped (already installed)\n")) {
                Console.log(Type.WARN, "Linking " + this.ref + " skipped (already installed)\n");
            }
            return;
        }
        try {
            Files.createSymbolicLink(link.toPath(), original.toPath());
            Console.log(Type.INFO, Style.DONE, " done\n");
        } catch (IOException e) {
            if (!Console.log(Type.INFO, Style.ERR, " failed\n")) {
                Console.log(Type.ERR, "Linking " + this.ref + " failed\n");
            }
        }
    }

    public void uninstall() { // TODO: Merge uninstall and unlink
        Console.log(Type.INFO, "Uninstalling " + this.ref);
        File file = new File("plugins/" + this.ref + ".jar");
        if (!file.exists()) {
            if (!Console.log(Type.INFO, Style.WARN, " skipped (not installed)\n")) {
                Console.log(Type.WARN, "Uninstalling " + this.ref + " skipped (not installed)\n");
            }
            return;
        }
        if (file.delete()) {
            Console.log(Type.INFO, Style.DONE, " done\n");
            return;
        }
        if(!Console.log(Type.INFO, Style.ERR, " failed\n")) {
            Console.log(Type.ERR, "Uninstalling " + this.ref + " failed\n");
        }
    }

    public void unlink() {
        Console.log(Type.INFO, "Unlinking " + this.ref);
        File file = new File("plugins/" + this.ref + ".jar");
        if (!Files.isSymbolicLink(file.toPath())) {
            if (!Console.log(Type.INFO, Style.WARN, " skipped (not a symbolic link)\n")) {
                Console.log(Type.WARN, "Uninstalling " + this.ref + " skipped (not a symbolic link)\n");
            }
            return;
        }
        if (file.delete()) {
            Console.log(Type.INFO, Style.DONE, " done\n");
            return;
        }
        if(!Console.log(Type.INFO, Style.ERR, " failed\n")) {
            Console.log(Type.ERR, "Deleting " + this.ref + " failed");
        }
    }

    @Override
    public Set<Plugin> plugins() {
        return Set.of(this);
    }

    @Override
    public @NotNull String toString() {
        return this.ref;
    }

    public static Plugin get(String ref) throws NotFoundException {
        for (Plugin p : Plugin.values()) {
            if (ref.equalsIgnoreCase(p.ref)) return p;
        }
        throw new NotFoundException(ref);
    }

    public static void list() {
        Console.logL(Type.REQUESTED, Style.PLUGIN, "Plugins", 4, 21, (Object[]) Plugin.values());
    }

    public static class NotFoundException extends InputException {
        public NotFoundException(@NotNull String ref) {
            super("Plugin \"" + ref + "\" not found");
        }
    }

    public final class URIError extends DataError {
        public URIError(@NotNull String uri, @NotNull Throwable cause) {
            super("Faulty url \n" + uri + "\n in plugin " + ref + ": " + cause.getMessage(), cause);
        }
    }
}
