public enum MessageType
{
    /**
     * SEARCH_FOR_ROUTE sent by train, searches for station. Tracks pass on and duplicate the message if needed.
     * RESERVE_ROUTE is sent by a station after a route is found. The route is secured as the sender list is popped back
     * to the train.
     * GO is sent by a station to a train ONLY. This message tells the train that a route has been found and is reserved
     * in that train's name, and that the track is set properly to protect it and to send it to the station it had requested.
     * The requested station's name is included in the GO message, as well as a 'signature' from the station who sent the message.
     * REQUEST_NEXT_TRACK is sent by a train ONLY to obtain a reference to the next track from its current track.
     * Tracks that receive this message send the request back to the train (by peeking at the sender list, seeing it's
     * a train, and using that reference). The return message contains  in the sender list first a reference to the
     * next IMessagable the train will proceed to AND THEN a reference to the track that sent the message (In the order
     * they'd be popped, respectively.)
     * TRAIN_GOODBYE_UNRESERVE
     *  Sent by a Train to the track piece it is just about to leave. The track piece then 'unreserves' itself.
     *  (Sent in proceedTo in Train.) Only the sender list and the message type will be checked on this message.
     */
    SEARCH_FOR_ROUTE, RESERVE_ROUTE, GO, REQUEST_NEXT_TRACK, TRAIN_GOODBYE_UNRESERVE
}
