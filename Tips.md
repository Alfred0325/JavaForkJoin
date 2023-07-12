In function BLAFair_getavoid：
> 1. 在p, q循环内每一次都提交到forkjoinpool任务太慢了，原因是每次提交任务至forjoinpool（即使是空任务）需要9ms左右的时间， 而在p，q循环内执行一次不到 1 ms（0 ms）
> 2. 尝试在每一个p循环内对全部的q（for q = ..; q < ...; q++）提交至一个forkjoinpool任务，任务时长仍然比sequential要慢
> 3. 由于是forkjoinpool， 需要改成诸如 “AtomicBoolean changed_y”，还有 “final int pFinal = p;” “changed_y.set(true);”， 这些操作也会导致同比sequential要慢大约 6ms
>

In function ComparePerformance.main():
> 1. 读进 两个automata后需要由 FiniteAutomaton进行 toBA() 操作否则sim和parallelsim结果不一致？？？（需探究为什么）
> 2. 同样的任务同样的function执行多次，执行时间会有较大差异，通常每执行一次，下一次执行时间会更短
> 3. la parameter between 1 and 12.
> 4. 关于读进的两个automata，如何选择（比如 是相同参数吗？）
>

问题：
> 1. 我该如何测试拆分p，q的程度 (由于本身每次执行时间都不确定)？
> 2. 有1000个文件data后，如何选取其中两个来插入比较
>

想法：
> 1. 由于在function BLAFair_getavoid 中，changed_x和changed_y并不直接关联，因此局部最优解可以导致全局最优解（即，对于每个小部分都要求时间最短，则整个function执行时间最短）
