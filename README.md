# Content of the solved problem:

ASD-Land is famous for its rich gold deposits, so for many years there was a flourishing sale of this

bullion to the neighboring kingdom, GRAF-Land. Unfortunately, a recently widening budget hole has forced GRAF-Land's king to impose prohibitive duties on metals and minerals. Merchants crossing the border must pay 50% of the value of the cargo they carry. ASD-Land merchants are threatened with bankruptcy. Fortunately, the country's alchemists have developed ways to change certain metals into others. The merchants' idea is to turn gold into a certain cheap metal with the help of alchemists, and then, after transporting it across the border and paying a small duty, get gold from it again. Unfortunately, alchemists have not found a way to change any metal into any other metal. So it may happen that the process of obtaining a given metal from gold has to go through multiple stages, and that at each stage a different metal is obtained. Alchemists charge a hefty price for their services, and for each known process of turning metal A into metal B they have set a price for transforming 1 kg of raw material. Traders wonder in what form gold should be transported across the border and what sequence of alchemical processes should be used to make the profits as high as possible.

## This program presents an efficient algorithm that:

* based on the prices of all metals, as well as the price of transformations offered by alchemists
will determine such a sequence of metals m0, m1, ..., mk that:
    * m0=mk is gold
    * for each i = 1...k alchemists can obtain metal mi from metal mi-1
    * the cost of performing the whole sequence of alchemical processes for 1 kg of gold plus the duty paid at the border (50% of the price of 1 kg of the cheapest metal mi, for i=0...k) is the least possible
* assume that during alchemical processes the weight of metals does not change
* write out the cost of performing the designated sequence of alchemical processes plus the duty paid at the border


## Input:

In the first line there is one positive integer n denoting the number of types of metals, 1 <= n <= 5000. In the line numbered k+1, for 1 <= k <= n, there is a non-negative even integer pk - the price of 1 kg of metal denoted by the number k, 0 <= pk <= 10^9. We assume that gold has the number 1. In the row numbered n + 2 there is one non-negative integer m equal to the number of transformation processes known to alchemists, 0 <= m <= 100000. In each of the next m rows there are three natural numbers, separated by single spaces, describing successive transformation processes. A trio of numbers a;b;c means that alchemists are able to obtain metal of number a from metal of number b and make themselves pay c asdollars for the conversion of 1 kg of raw material, 1 <= a, b <=n, 0 <= c <= 10000. An ordered pair of numbers a and b can appear at most once in the data.

## Output:

The first line should output one integer - the cost of performing the designated sequence of alchemical processes plus the duty paid at the border.

