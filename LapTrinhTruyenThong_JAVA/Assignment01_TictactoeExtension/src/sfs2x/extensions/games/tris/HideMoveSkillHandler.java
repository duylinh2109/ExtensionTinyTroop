package sfs2x.extensions.games.tris;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class HideMoveSkillHandler extends BaseClientRequestHandler {

	@Override
	public void handleClientRequest(User user, ISFSObject params) {
		// TODO Auto-generated method stub
		TrisExtension gameExt = (TrisExtension) getParentExtension();
		
		ISFSObject respObj = new SFSObject();
		
		if(user.getPlayerId()==1)
		{
			gameExt.send("hidemove", respObj, gameExt.getGameRoom().getUserByPlayerId(2) );
		}
		else if(user.getPlayerId()==2)
		{
			gameExt.send("hidemove", respObj, gameExt.getGameRoom().getUserByPlayerId(1) );
		}
	}
}