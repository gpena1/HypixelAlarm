import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import net.hypixel.api.HypixelAPI;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class HypixelAlarm {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HypixelAPI api = new HypixelAPI(UUID.fromString("api key here"));
        // IN THE VARIABLE BELOW, PUT THE UUID OF THE PERSON WHOSE STATUS YOU WOULD LIKE TO TRACK.
        // YOU CAN GET THE UUID BY GOING TO NAMEMC.COM AND TYPING THEIR NAME IN THE SEARCH BAR.
        String uuid = "uuid here";
        int lastLogin = api.getPlayerByUuid(uuid).get().getPlayer().get("lastLogin").getAsInt();
        int lastLogout = api.getPlayerByUuid(uuid).get().getPlayer().get("lastLogout").getAsInt();
        while (true){
            lastLogout = api.getPlayerByUuid(uuid).get().getPlayer().get("lastLogout").getAsInt();
            lastLogin = api.getPlayerByUuid(uuid).get().getPlayer().get("lastLogin").getAsInt();
            if (lastLogin > lastLogout) break;
            System.out.println("Still not online...");
            Thread.sleep(1000);
        }
        playMusic("alarm.mp3");
    }
    private static void playMusic(String path){
        try{
            FileInputStream fis = new FileInputStream(path);
            Player p = new Player(fis);
            p.play();
        }catch(FileNotFoundException | JavaLayerException e){
            e.printStackTrace();
        }
    }
}
