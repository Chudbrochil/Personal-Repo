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
     * REQUEST_NEXT_TRACK is sent by a train ONLY to obtain a reference to the next track from its current track.
     *   Tracks that receive this message send the request back to the train (by peeking at the sender list, seeing it's
     *   a train, and using that reference). The return message contains  in the sender list first the track that sent
     *   the message AND THEN a reference to the next IMessagable the train will proceed to.
     */
    HELLOTEST, SEARCH_FOR_ROUTE, RESERVE_ROUTE, GO, REQUEST_NEXT_TRACK
}
