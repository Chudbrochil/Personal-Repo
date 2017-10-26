public enum MessageType
{
    /**
     * HELLOTEST sends messages down the line to the right.
     * SEARCH_FOR_ROUTE sent by train, searches for station. Tracks pass on and duplicate the message if needed.
     * RESERVE_ROUTE is sent by a station after a route is found. The route is secured as the sender list is popped back
     *   to the train.
     */
    HELLOTEST, SEARCH_FOR_ROUTE, RESERVE_ROUTE
}
