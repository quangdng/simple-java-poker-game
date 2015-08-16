## Project Idea
A simple java poker game that takes n number of player hands and produce output for the winner.

## Example
For example, this input:

java Poker 2H TH AS AD TC

should produce this output:

Player␣1:␣Aces␣over␣10s

This input:

java Poker 2H TH 1S 1D TC

Should produce this output:

Error:␣invalid␣card␣name␣’1S’

This input:

java Poker KS 9S QS AS JS 3D 7C 3S 3H 7S

Should produce this output:

Player␣1:␣Ace-high␣flush
Player␣2:␣3s␣full␣of␣7s
Player␣2␣wins.

This input:

java Poker qc jc 2h 7s 9h qd jd 2s 7c 9s 9c 7d 2c jh qh 9d 7h 2d js qs

Should produce this output:
Player␣1:␣Queen-high
Player␣2:␣Queen-high
Player␣3:␣Queen-high
Player␣4:␣Queen-high
Players␣1,␣2,␣3␣and␣4␣draw.

Note that spaces in these sample outputs are shown as ␣.
