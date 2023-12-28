    //region Day23
    @Theory
    public void Day23Part1(@FromDataPoints("example") boolean example) {

        var actual = Day23.Part1.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    @Theory
    public void Day23Part2(@FromDataPoints("example") boolean example) {

        var actual = Day23.Part2.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    //endregion
}