package sites.linkedIn.io;

public enum PersonExcelColumn implements Column{
    PERSON_NAME(0), PERSON_DESCRIPTION(1);
    private int index;
    /**
     * Returns index of the Person Column
     * @return index
     */
    PersonExcelColumn(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return index;
    }
}
