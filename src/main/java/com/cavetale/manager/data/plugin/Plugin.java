package com.cavetale.manager.data.plugin;

import com.cavetale.manager.data.DataError;
import com.cavetale.manager.parser.InputException;
import com.cavetale.manager.util.Util;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.Type;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.Set;

/**
 * List of available plugins
 */
public enum Plugin implements Provider {
    // TODO: Adventure
    AdviceAnimals("com.winthier.adviceanimals", "0.1-SNAPSHOT", Category.DEPRECATED),
    AFK("com.cavetale.afk", "0.1-SNAPSHOT", Category.GLOBAL),
    AntiPopup(Util.uriOf("https://github.com/KaspianDev/AntiPopup/releases/download/b7a08d9/AntiPopup-9.2.jar"), "9.2", Category.GLOBAL),
    Area("com.cavetale.area", "0.1-SNAPSHOT", Category.GLOBAL),
    ArmorStandEditor("io.github.rypofalem.armorstandeditor", "1.17-25", Category.GLOBAL),
    Auction("com.cavetale.auction", "0.1-SNAPSHOT", Category.GLOBAL),
    Bans("com.winthier.bans", "0.1-SNAPSHOT", Category.GLOBAL),
    Bingo("com.cavetale.bingo", "0.1-SNAPSHOT", Category.MINI_GAME),
    BlockClip("com.cavetale.blockclip", "0.1-SNAPSHOT", Category.GLOBAL),
    BlockTrigger("com.cavetale.blocktrigger", "0.1-SNAPSHOT", Category.GLOBAL),
    // TODO: BungeeCavetale
    // TODO: CaptureTheFlag
    Caves("com.cavetale.caves", "0.1-SNAPSHOT", Category.WORLD_GEN),
    // TODO: CavetaleResourcePack
    Chair("com.cavetale.chair", "0.1-SNAPSHOT", Category.BUILD),
    Chat("com.winthier.chat", "0.1-SNAPSHOT", Category.CORE),
    // TODO: Chess
    Christmas("com.cavetale.christmas", "0.1-SNAPSHOT", Category.DEPRECATED),
    Colorfall("io.github.feydk.colorfall", "0.1-SNAPSHOT", Category.MINI_GAME),
    Connect("com.winthier.connect", "0.1-SNAPSHOT", Category.CORE),
    // TODO: ConnectCore
    Core("com.cavetale.core", "0.1-SNAPSHOT", Category.CORE),
    Countdown("com.winthier.countdown", "0.1", Category.GLOBAL),
    CraftBay("com.winthier.craftbay", "2.26-SNAPSHOT", Category.DEPRECATED),
    Creative("com.winthier.creative", "0.1-SNAPSHOT", Category.CREATIVE),
    CullMob("com.cavetale.cullmob", "0.1-SNAPSHOT", Category.HOME),
    Decorator("com.winthier.decorator", "0.1-SNAPSHOT", Category.WORLD_GEN),
    Dungeons("com.cavetale.dungeons", "0.1-SNAPSHOT", Category.MINE),
    Dusk("com.winthier.dusk", "0.1", Category.SURVIVAL),
    Easter("com.cavetale.easter", "0.1-SNAPSHOT", Category.SEASONAL),
    Editor("com.cavetale.editor", "0.1-SNAPSHOT", Category.GLOBAL),
    Election("com.cavetale.election", "0.1-SNAPSHOT", Category.SURVIVAL),
    Enderball("com.cavetale.enderball", "0.1-SNAPSHOT", Category.MINI_GAME),
    Enemy("com.cavetale.enemy", "0.1-SNAPSHOT", Category.SURVIVAL),
    Exploits("com.winthier.exploits", "0.1-SNAPSHOT", Category.SURVIVAL),
    ExtremeGrassGrowing("com.cavetale.extremegrassgrowing", "0.1-SNAPSHOT", Category.HUB),
    Fam("com.cavetale.fam", "0.1-SNAPSHOT", Category.GLOBAL),
    FastLeafDecay("com.cavetale.fastleafdecay", "1.0-SNAPSHOT", Category.SURVIVAL),
    Festival("com.cavetale.festival", "0.1-SNAPSHOT", Category.SEASONAL),
    FlatGenerator("com.cavetale.flatgenerator", "0.1-SNAPSHOT", Category.CREATIVE),
    Fly("com.cavetale.fly", "0.1-SNAPSHOT", Category.GLOBAL),
    FreeHat("com.cavetale.freehat", "0.1-SNAPSHOT", Category.BUILD),
    GoldenTicket("com.cavetale.goldenticket", "0.1-SNAPSHOT", Category.SURVIVAL),
    Halloween("com.cavetale.halloween", "0.1-SNAPSHOT", Category.DEPRECATED),
    HideAndSeek("com.cavetale.hideandseek", "0.1-SNAPSHOT", Category.MINI_GAME),
    Home("com.cavetale.home", "0.1-SNAPSHOT", Category.GLOBAL),
    HopperFilter("com.winthier.hopperfilter", "0.1-SNAPSHOT", Category.SURVIVAL),
    HotSwap("com.cavetale.hotswap", "0.1-SNAPSHOT", Category.GLOBAL),
    Inventory("com.cavetale.inventory", "0.1-SNAPSHOT", Category.GLOBAL),
    InvisibleItemFrames("com.cavetale.invisibleitemframes", "0.1-SNAPSHOT", Category.DEPRECATED),
    ItemStore("com.winthier.itemstore", "0.1-SNAPSHOT", Category.GLOBAL),
    KeepInventory("com.winthier.keepinventory", "0.1-SNAPSHOT", Category.SURVIVAL),
    KingOfTheLadder("com.cavetale.kotl", "kotl", "0.1-SNAPSHOT", Category.HUB),
    // TODO: KingOfTheRing
    Kit("com.winthier.kit", "0.1", Category.GLOBAL),
    // TODO: LastLog
    LinkPortal("com.winthier.linkportal", "0.1-SNAPSHOT", Category.DEPRECATED),
    MagicMap("com.cavetale.magicmap", "0.1-SNAPSHOT", Category.GLOBAL),
    Mail("com.winthier.mail", "0.1-SNAPSHOT", Category.GLOBAL),
    MapLoad("com.cavetale.mapload", "0.1-SNAPSHOT", Category.UTIL),
    MassStorage("com.cavetale.massstorage", "0.1-SNAPSHOT", Category.SURVIVAL),
    Maypole("com.winthier.maypole", "0.1", Category.SEASONAL),
    MemberList("com.cavetale.memberlist", "0.1-SNAPSHOT", Category.GLOBAL),
    Menu("com.cavetale.menu", "0.1-SNAPSHOT", Category.GLOBAL),
    Merchant("com.cavetale.merchant", "0.1-SNAPSHOT", Category.BUILD),
    Miniverse("com.cavetale.miniverse", "0.1-SNAPSHOT", Category.UTIL),
    // TODO: MobArena
    Money("com.cavetale.money", "0.1-SNAPSHOT", Category.CORE),
    Mytems("com.cavetale.mytems", "0.1-SNAPSHOT", Category.CORE),
    // TODO: NBTDump
    OpenInv(Util.uriOf("https://github.com/Jikoo/OpenInv/releases/download/5.1.1/OpenInv.jar"), "5.1.1", Category.GLOBAL),
    Overboard("com.cavetale.overboard", "0.1-SNAPSHOT", Category.MINI_GAME),
    Perm("com.winthier.perm", "0.1-SNAPSHOT", Category.CORE),
    Photos("com.winthier.photos", "0.1-SNAPSHOT", Category.BUILD),
    Picitonary("com.cavetale.pictionary", "0.1-SNAPSHOT", Category.CREATIVE),
    PlayerCache("com.winthier.playercache", "0.1-SNAPSHOT", Category.CORE),
    PlayerInfo("com.winthier.playerinfo", "0.1-SNAPSHOT", Category.GLOBAL),
    PlugInfo("com.cavetale.pluginfo", "0.1-SNAPSHOT", Category.GLOBAL),
    PocketMob("com.cavetale.pocketmob", "0.1-SNAPSHOT", Category.SURVIVAL),
    Poster("com.cavetale.poster", "0.1-SNAPSHOT", Category.SURVIVAL),
    Protect("com.winthier.protect", "0.1-SNAPSHOT", Category.GLOBAL),
    ProtocolLib("com.comphenix.protocol", "4.7.1-SNAPSHOT", Category.UTIL), // TODO: External download
    PVPArena("com.cavetale.pvparena", "0.1-SNAPSHOT", Category.MINI_GAME),
    // TODO: Quidditch
    Quiz("com.winthier.quiz", "0.1-SNAPSHOT", Category.DEPRECATED),
    Race("com.cavetale.race", "0.1-SNAPSHOT", Category.MINI_GAME),
    Raid("com.cavetale.raid", "0.1-SNAPSHOT", Category.MINI_GAME),
    RandomPlayerHead("com.winthier.rph", "random-player-head", "0.1-SNAPSHOT", Category.BUILD),
    RedGreenLight("com.cavetale.redgreenlight", "0.1-SNAPSHOT", Category.HUB),
    Resident("com.cavetale.resident", "0.1-SNAPSHOT", Category.SURVIVAL),
    Resource("com.winthier.resource", "0.1", Category.SURVIVAL),
    ResourcePack("com.cavetale.resourcepack", "0.1-SNAPSHOT", Category.GLOBAL),
    Rules("com.winthier.rules", "0.1-SNAPSHOT", Category.GLOBAL),
    Server("com.cavetale.server", "0.1-SNAPSHOT", Category.GLOBAL),
    ServerStatus("com.cavetale.serverstatus", "0.1-SNAPSHOT", Category.DEPRECATED),
    Shop("com.winthier.shop", "0.1-SNAPSHOT", Category.SURVIVAL),
    Shutdown("com.winthier.shutdown", "0.1-SNAPSHOT", Category.GLOBAL),
    Sidebar("com.cavetale.sidebar", "0.1-SNAPSHOT", Category.CORE),
    SignSpy("com.cavetale.signspy", "0.1-SNAPSHOT", Category.BUILD),
    Skills("com.cavetale.skills", "0.1-SNAPSHOT", Category.SURVIVAL),
    // TODO: SkyBlock
    Spawn("com.winthier.spawn", "0.1-SNAPSHOT", Category.GLOBAL),
    Spike("com.cavetale.spike", "0.1-SNAPSHOT", Category.GLOBAL),
    Spleef("com.winthier.spleef", "0.1-SNAPSHOT", Category.MINI_GAME),
    SQL("com.winthier.sql", "0.1-SNAPSHOT", Category.CORE),
    StarBook("com.winthier.starbook", "0.1-SNAPSHOT", Category.GLOBAL),
    StopRain("com.winthier.stoprain", "0.1-SNAPSHOT", Category.GLOBAL),
    Streamer("com.cavetale.streamer", "0.1-SNAPSHOT", Category.GLOBAL),
    Structure("com.cavetale.structure", "0.1-SNAPSHOT", Category.SURVIVAL),
    SurvivalGames("com.cavetale.survivalgames", "0.1-SNAPSHOT", Category.MINI_GAME),
    Televator("com.cavetale.televator", "0.1-SNAPSHOT", Category.GLOBAL),
    // TODO: Territory
    // TODO: Tetris
    Ticket("com.winthier.ticket", "0.1-SNAPSHOT", Category.GLOBAL),
    Tinfoil("com.winthier.tinfoil", "0.1", Category.GLOBAL),
    Title("com.winthier.title", "0.1-SNAPSHOT", Category.CORE),
    TooManyEntities("com.winthier.toomanyentities", "0.1", Category.BUILD),
    TPA("com.cavetale.tpa", "0.1-SNAPSHOT", Category.GLOBAL),
    Trees("com.cavetale.trees", "0.1-SNAPSHOT", Category.BUILD),
    Tutor("com.cavetale.tutor", "0.1-SNAPSHOT", Category.GLOBAL),
    Vertigo("io.github.feydk.vertigo", "0.1-SNAPSHOT", Category.MINI_GAME),
    VoidGenerator("com.cavetale.voidgenerator", "0.1-SNAPSHOT", Category.CORE),
    Vote("com.cavetale.vote", "0.1-SNAPSHOT", Category.GLOBAL),
    Wall("com.winthier.wall", "0.1-SNAPSHOT", Category.GLOBAL),
    Wardrobe("com.cavetale.wardrobe", "0.1-SNAPSHOT", Category.GLOBAL),
    Warp("com.cavetale.warp", "0.1-SNAPSHOT", Category.GLOBAL),
    Watchman("com.cavetale.watchman", "0.1-SNAPSHOT", Category.BUILD),
    // TODO: Waterfall
    // TODO: Windicator
    WinTag("com.cavetale.wintag", "0.1-SNAPSHOT", Category.BUILD),
    WorldEdit(Util.uriOf("https://dev.bukkit.org/projects/worldedit/files/5613179/download"), "7.3.6", Category.GLOBAL),
    WorldMarker("com.cavetale.worldmarker", "0.1-SNAPSHOT", Category.CORE),
    Worlds("com.winthier.worlds", "0.1-SNAPSHOT", Category.GLOBAL),
    Xmas("com.cavetale.xmas", "0.1-SNAPSHOT", Category.SEASONAL);

    private final @NotNull Source source;
    public final @NotNull Category[] categories;

    Plugin(@NotNull URI uri, @NotNull String version, @NotNull Category... categories) {
        this.source = new Other(uri, version);
        this.categories = categories;
    }

    Plugin(@NotNull String groupId, @NotNull String version, @NotNull Category... categories) {
        this.source = new Jenkins(this.name(), groupId, this.name().toLowerCase(), version);
        this.categories = categories;
    }

    Plugin(@NotNull String groupId, @NotNull String artifactId, @NotNull String version,
           @NotNull Category... categories) {
        this.source = new Jenkins(this.name(), groupId, artifactId, version);
        this.categories = categories;
    }

    public void install(@NotNull Set<Plugin> installed) {
        Console.log(Type.INFO, "Installing " + this.name());
        if (installed.contains(this)) {
            if (!Console.log(Type.INFO, Style.WARN, " skipped (already installed)\n")) {
                Console.log(Type.WARN, "Installing " + this.name() + " skipped (already installed)\n");
            }
            return;
        }
        File file = new File("plugins/" + this.name() + "-" + this.source.version + ".jar");
        try {
            Util.download(this.source.uri, file);
            Console.log(Type.INFO, Style.DONE, " done\n");
        } catch (IOException e) {
            if (!Console.log(Type.INFO, Style.ERR, " failed\n")) {
                Console.log(Type.ERR, "Installing " + this.name() + " failed\n");
            }
        }
    }

    public void link(@NotNull String path, @NotNull Set<Plugin> installed) {
        Console.log(Type.INFO, "Linking " + this.name());
        File origin = null;
        File originFolder = new File(path);
        originFolder.mkdir();
        String name;
        for (File f : originFolder.listFiles()) {
            name = f.getName();
            if (name.toLowerCase().startsWith(this.name().toLowerCase()) && name.endsWith(".jar")) {
                origin = f;
            }
        }
        if (origin == null) {
            if (!Console.log(Type.INFO, Style.WARN, " failed (origin not found)\n")) {
                Console.log(Type.WARN, "Linking " + this.name() + " failed (origin not found)\n");
            }
            return;
        }
        if (installed.contains(this)) {
            if (!Console.log(Type.INFO, Style.WARN, " skipped (already installed)\n")) {
                Console.log(Type.WARN, "Linking " + this.name() + " skipped (already installed)\n");
            }
            return;
        }
        File folder = new File("plugins/");
        File link = new File(folder, origin.getName());
        try {
            Files.createSymbolicLink(link.toPath(), origin.toPath());
            Console.log(Type.INFO, Style.DONE, " done\n");
        } catch (IOException e) {
            if (!Console.log(Type.INFO, Style.ERR, " failed\n")) {
                Console.log(Type.ERR, "Linking " + this.name() + " failed\n");
            }
        }
    }

    // TODO: add update method

    public void uninstall() {
        File folder = new File("plugins/");
        File[] files = folder.listFiles();
        if (files == null) return;
        String name;
        for (File f : files) {
            name = f.getName();
            if (name.toLowerCase().startsWith(this.name().toLowerCase()) && name.toLowerCase().endsWith(".jar")) {
                Console.log(Type.INFO, "Uninstalling " + name);
                if (f.delete()) {
                    Console.log(Type.INFO, Style.DONE, " done\n");
                    continue;
                }
                Console.log(Type.ERR, "Uninstalling " + this.name() + " failed\n");
            }
        }
    }

    @Override
    public Set<Plugin> plugins() {
        return Set.of(this);
    }

    @Override
    public @NotNull String toString() {
        return this.name();
    }

    public static Plugin get(String ref) throws NotFoundException {
        for (Plugin p : Plugin.values()) {
            if (ref.toLowerCase().startsWith(p.name().toLowerCase()) &&
                    ref.toLowerCase().endsWith(".jar")) return p;
        }
        throw new NotFoundException(ref);
    }

    public static void list() {
        Console.logL(Type.REQUESTED, Style.PLUGIN, "Plugins", 4, 21, (Object[]) Plugin.values());
    }



    private static abstract class Source {
        public final @NotNull String version;
        public final @NotNull URI uri;

        public Source(@NotNull String version, @NotNull URI uri) {
            this.version = version;
            this.uri = uri;
        }
    }

    public static class Jenkins extends Source {
        public Jenkins(@NotNull String jobId, @NotNull String groupId, @NotNull String artifactId, @NotNull String version) {
            super(version, Util.uriOf("https://cavetale.com/jenkins/job/" + jobId +
                    "/lastSuccessfulBuild/" + groupId + "$" + artifactId + "/artifact/" + groupId +
                    "/" + artifactId + "/" + version + "/" + artifactId + "-" + version + ".jar"));
        }
    }

    public static class Other extends Source {
        public Other(@NotNull URI uri, @NotNull String version) {
            super(version, uri);
        }
    }

    public static class NotFoundException extends InputException {
        public NotFoundException(@NotNull String ref) {
            super("Plugin \"" + ref + "\" not found");
        }
    }

    public static class URIError extends DataError {
        public URIError(@NotNull String uri, @NotNull Throwable cause) {
            super("Faulty url \n" + uri + ": " + cause.getMessage(), cause);
        }
    }
}
