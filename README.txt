  ______                 ____                  ___    __      __
 /_  __/_  ______  ___  / __ )____ _________  /   |  / /___  / /_  ____ _
  / / / / / / __ \/ _ \/ __  / __ `/ ___/ _ \/ /| | / / __ \/ __ \/ __ `/
 / / / /_/ / / / /  __/ /_/ / /_/ (__  )  __/ ___ |/ / /_/ / / / / /_/ /
/_/  \__,_/_/ /_/\___/_____/\__,_/____/\___/_/  |_/_/ .___/_/ /_/\__,_/  v1.2
By: Devini15                                       /_/

Developer Contact: https://devini15.live/

New This Version:
    - "Add & Clear" button now just "Clear"
    - Pressing "enter" in any field now adds the next note without needing to click "Add"
    - More phonemes added to readme
    - Made it so the clear button actually clears all 3 fields instead of just the 1st 2

Console Colors
==============
If you see a message that you do not have console colors working, the script may give bad output. There are 2 ways to fix this:
	1. Enable Console Colors
	    - Open administrator command prompt and enter: reg add HKEY_CURRENT_USER\Console /v VirtualTerminalLevel /t REG_DWORD /d 0x00000001 /f
	2. Get the No Color version
	    - Go to the GitHub repository and download the No Colors version of the script.

User Guide
==========
1. Download and extract the latest version from releases
2. Run "TuneBase.bat" (you can also run the jar file directly, but will not be able to see the console output)
3. Select the desired note from the drop-down
4. Enter the duration the note should play as an integer (in milliseconds) into the duration field.
5. Enter the phoneme you'd like to be sung into the phoneme field (DECtalk phonemes are a bit janky, so you may need to experiment a little)
6. Click a button
	- Add: Adds the specified note to the final output
	- Add & Clear Fields: Adds the specified note to the final output, resets the duration and phoneme fields to blank
	- Undo: Removes the most recently added note from the final output
	- Finish: Allows you to generate the output as a string that can be copy/pasted into DECtalk.
		- Selecting "Yes" when asked to make a file will either create or overwrite the contents of output.txt in the folder you run the jar from.
7. Paste the output into DECtalk, you are done!

Known Phonemes
==============
aa (box, hot)
ah (bald, war)
ax (about, tuba)
ae (tad, apple)
ay (tie, rye)
ao ("owe uhh")
aw (loud, down)
ar (tar, bark)
ey (day, drain)
eh (wet, bent)
iy (bee, bean)
ih (tip, flint)
ix (tip, flint)
ir (ear, steer)
ow (boat, soul)
or (corn, fort)
uh (punt, run)
uw (root, rule)
yu (you, cute)

nx (bang, gong)
jh (*sound of television static*)
hx (*I don't know how to describe this but it's weird*)
zh (measure)
