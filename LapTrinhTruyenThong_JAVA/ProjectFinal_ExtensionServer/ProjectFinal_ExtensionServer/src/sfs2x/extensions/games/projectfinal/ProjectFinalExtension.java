package sfs2x.extensions.games.projectfinal;

import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.SFSExtension;

public class ProjectFinalExtension
        extends SFSExtension {

    private User whoseTurn;
    private volatile boolean gameStarted;
    private final String version = "1.0";

    public int numUserInRoomIsReady;

    public void init() {
        trace(new Object[]{"Welcome to MOBA-Gunny!"});
        trace(new Object[]{"1.0"});

        this.numUserInRoomIsReady = 0;

        addRequestHandler("playerready", ReadyHandler.class);
        addEventHandler(SFSEventType.USER_LOGIN, LoginHandler.class);
    }

    public boolean isGameStarted() {
        return this.gameStarted;
    }

    public void startGame() {
        if (this.gameStarted) {
            throw new IllegalStateException("game is already started!");
        }
        User player1 = getParentRoom().getUserByPlayerId(1);
        User player2 = getParentRoom().getUserByPlayerId(2);

        if (this.whoseTurn == null) {
            this.whoseTurn = player1;
        }

        ISFSObject resObj = new SFSObject();
        resObj.putInt("t", this.whoseTurn.getPlayerId());
        resObj.putUtfString("p1n", player1.getName());
        resObj.putInt("p1i", player1.getId());
        resObj.putUtfString("p2n", player2.getName());
        resObj.putInt("p2i", player2.getId());

        send("startgame", resObj, getParentRoom().getUserList());
    }

    public void stopGame() {
    }

    Room getGameRoom() {
        return getParentRoom();
    }

    public void setNumUserIsReady(int num) {
        this.numUserInRoomIsReady = num;
    }

    public int getNumUserIsReady() {
        return this.numUserInRoomIsReady;
    }
}


/* Location:              C:\Program Files\SmartFoxServer_2X\SFS2X\extensions\SFSExtensions\projectfinalExtension.jar!\sfs2x\extensions\games\projectfinal\ProjectFinalExtension.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
