  ______                 ____                  ___    __      __
 /_  __/_  ______  ___  / __ )____ _________  /   |  / /___  / /_  ____ _
  / / / / / / __ \/ _ \/ __  / __ `/ ___/ _ \/ /| | / / __ \/ __ \/ __ `/
 / / / /_/ / / / /  __/ /_/ / /_/ (__  )  __/ ___ |/ / /_/ / / / / /_/ /
/_/  \__,_/_/ /_/\___/_____/\__,_/____/\___/_/  |_/_/ .___/_/ /_/\__,_/  v1.1
By: Devini15                                       /_/

Developer Contact: https://devini15.live/

New This Version:
    - Phoneme list in readme
    - Added "Output so Far" button
    - Shifted octave labels to be accurate
    - Added "Ending Consonants" field
    - Added No Color mode if any argument is used when launching

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
- aa = box
- ah = bald
- ae = tap
- ay = tie
- ao = owe uhh
- aw = loud (may change depending on whatever follows)
- ar = tar
- ey = way
- eh = wet
- iy = bean
- ih = tip
- ix = tip
- ir = ear
- ow = boat
- uh = punt
- uw = tool
- jh = [sound of television static]
- hx = I honestly don't know but it's weird
