package fct.unl.pt.instagramplus.Models.Reactions;

public enum ReactionType {

    LIKE(0),
    LOVE(1),
    AHAH(2),
    WOW(3),
    ANGRY(4),
    SAD(5);

    private int value;

    public int getValue()
    {
        return this.value;
    }

    ReactionType(int value)
    {
        this.value = value;
    }

}
