#ViceChat#

## A terminal based chat coded in Java.

**ViceProtocol msgs:**

 #VICE_CONN/<nickname> // #VICE_CONN/<nickname or empty if the name is already in use>
 #VICE_SHOW_ROOMS/<empty> // #VICE_SHOW_ROOMS/<names of the rooms>
 #VICE_ENTER_ROOM/<chatroom>
 #VICE_SHOW_USER/<empty> // #VICE_SHOW_USER/<users nicknames>
 #VICE_MSG/<msg to the room>
 #VICE_PRIV_CHAT/<nickname of the user with which start the private chat> // #VICE_PRIV_CHAT/<nickname of the user with which start the private  chat or empty if the user rejected the invitation to chat>
 #VICE_END_PRIV_CHAT/<nickname of the user>
 #VICE_NEW_ROOM/<name of the new room>
 #VICE_CLOSE/<empty>