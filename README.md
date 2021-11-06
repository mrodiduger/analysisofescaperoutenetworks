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
5 > print ABC<br/>
6 a4b<br/>
7 a2c<br/>
8 b3c<br/>
9 b1d<br/>
10 c6d<br/>
11 > flow ABC a d<br/>
12 6<br/>
13 > list ABC<br/>
14 6 a d<br/>
15 > add ABC a5d<br/>
16 Added new section a5d to escape network ABC.<br/>
17 > print ABC<br/>
18 a4b<br/>
19 a2c<br/>
20 a5d<br/>
21 b3c<br/>
22 b1d<br/>
23 c6d<br/>
24 > list ABC<br/>
25 EMPTY<br/>
26 > add XYZ a10b;a10c;b2c;b4d;b8e;c9e;d10f;e10f;e6d<br/>
27 Added new escape network with identifier XYZ.<br/>
28 > list<br/>
29 XYZ 6<br/>
30 ABC 4<br/>
31 > print XYZ<br/>
32 a10b<br/>
33 a10c<br/>
34 b2c<br/>
35 b4d<br/>
36 b8e<br/>
37 c9e<br/>
38 d10f<br/>
39 e6d<br/>
40 e10f<br/>
41 > flow XYZ a f<br/>
42 19<br/>
43 > list XYZ<br/>
44 19 a f<br/>
45 > quit<br/>
