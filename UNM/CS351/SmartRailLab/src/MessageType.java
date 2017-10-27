public enum MessageType
{
    /**
     * HELLOTEST sends messages down the line to the right.
     * SEARCH_FOR_ROUTE sent by train, searches for station. Tracks pass on and duplicate the message if needed.
     * RESERVE_ROUTE is sent by a station after a route is found. The route is secured as the sender list is popped back
     *   to the train.
     * GO is sent by a station to a train ONLY. This message tells the train that a route has been found and is reserved
     *   in that train's name, and that the track is set properly to protect it and to send it to the station it had requested.
     *   The requested station's name is included in the GO message, as well as a 'signature' from the station who sent the message.
     */
    HELLOTEST, SEARCH_FOR_ROUTE, RESERVE_ROUTE, GO
}
