# route-graph-maximal-flow-calculator
The program analyzes escape networks in directed graph form and manages them with some commands.<br/>

It is written as a part of a first semester Java programming course. The program takes a directed graph as an input. Every edge of the graph has a pre-given capacity<br/>
and with the help of the Edmonds-Karp algorithm the program calculates the maximal flow from a start node to an end node.<br/>
(Terminal.class is pre given)

Interaction example:<br/>
1 > add ABC a4b;a2c;b1d;b3c;c6d<br/>
2 Added new escape network with identifier ABC.<br/>
3 > list<br/>
4 ABC 4<br/>
5 > print ABC\n
6 a4b\n
7 a2c\n
8 b3c\n
9 b1d\n
10 c6d\n
11 > flow ABC a d\n
12 6
13 > list ABC
14 6 a d
15 > add ABC a5d
16 Added new section a5d to escape network ABC.
17 > print ABC
18 a4b
19 a2c
20 a5d
21 b3c
22 b1d
23 c6d
24 > list ABC
25 EMPTY
26 > add XYZ a10b;a10c;b2c;b4d;b8e;c9e;d10f;e10f;e6d
27 Added new escape network with identifier XYZ.
28 > list
29 XYZ 6
30 ABC 4
31 > print XYZ
32 a10b
33 a10c
34 b2c
35 b4d
36 b8e
37 c9e
38 d10f
39 e6d
40 e10f
41 > flow XYZ a f
42 19
43 > list XYZ
44 19 a f
45 > quit
