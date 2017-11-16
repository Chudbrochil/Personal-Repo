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
     * ABORT_ROUTE_RESERSVE
     *   Sent by Rail components if a RESERVE_ROUTE message reaches a track that is currently reserved. If a component
     *   that receives a RESERVE_ROUTE is already reserved, it calls reverseRouteList() on the message to be able
     *   to send it backwards and changes the message type to ABORT_ROUTE_RESERVE. Then it unreserves itself and the
     *   message is forwarded to the next IMessagable in that list.
     * WAIT_FOR_CLEAR_ROUTE
     *   Issued by a Rail component if it receives a RESERVE_ROUTE but is already reserved. This message gets sent to
     *   the train and is forwarded on by all rail pieces and indicates that the train should wait for a time and then
     *   request a route to the same destination again.
     *
     */
    SEARCH_FOR_ROUTE, RESERVE_ROUTE, GO, REQUEST_NEXT_TRACK, TRAIN_GOODBYE_UNRESERVE, ABORT_RESERVE_ROUTE, WAIT_FOR_CLEAR_ROUTE
}
