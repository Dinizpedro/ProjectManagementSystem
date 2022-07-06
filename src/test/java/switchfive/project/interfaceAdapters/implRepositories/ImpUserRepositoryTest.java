package switchfive.project.interfaceAdapters.implRepositories;

class ImpUserRepositoryTest {

//TODO
/*    @Test
    void shouldAssertEqualsComparingAListWithTheSameUsers() {
        User userMock = mock(User.class);
        ImplUserRepository aStore = new ImplUserRepository();
        List<User> expected = new ArrayList<>();

        expected.add(userMock);
        aStore.save(userMock);
        List<User> actual = aStore.getUserList();

        assertEquals(expected, actual);
    }

    @Test
    void shouldAssertNotEqualsComparingAListWithDifferentUsers() {
        User userMockOne = mock(User.class);
        User userMockTwo = mock(User.class);
        ImplUserRepository aStore = new ImplUserRepository();
        List<User> expected = new ArrayList<>();

        expected.add(userMockOne);
        aStore.save(userMockTwo);
        List<User> actual = aStore.getUserList();

        assertNotEquals(actual, expected);
    }*/

    //TODO
/*    @Test
    void shouldReturnAUserWhenSavingItToTheStore() {
        User actual = mock(User.class);
        ImplUserRepository aStore = new ImplUserRepository();

        User expected = aStore.save(actual);

        assertEquals(actual, expected);
    }*/

//TODO
/*    @Test
    void userEmailIsFoundSuccessfully() {
        User userMock = mock(User.class);
        when(userMock.compareEmail("valid@email.com")).thenReturn(true);

        ImplUserRepository aStore = new ImplUserRepository();

        aStore.save(userMock);

        Optional<User> expected = aStore.findByEmail("valid@email.com");
        Optional<User> actual = Optional.of(userMock);
        assertEquals(expected, actual);
    }

    @Test
    void ShouldReturnTrueAssertingAValidIdForAUser() {
        User userMock = mock(User.class);
        ImplUserRepository aStore = new ImplUserRepository();

        aStore.save(userMock);

        int expected = 2;
        int actual = aStore.getNextUserID();

        assertEquals(expected, actual);
    }

    @Test
    void ShouldReturnFalseAssertingAnInvalidIdForAUser() {
        User userMock = mock(User.class);
        ImplUserRepository aStore = new ImplUserRepository();

        aStore.save(userMock);

        int expected = 1;
        int actual = aStore.getNextUserID();

        assertNotEquals(expected, actual);
    }*/

    void test() {

    }
}
