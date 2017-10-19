package track;

/**
 * Anna Carey on 10/18/17
 *
 * Interface for all track pieces. Assumes a train can sit on it and it has neighbors.
 *
 */
public interface TrackSegment
{
  public void reserve();   //Reserves the track and prevents any other traffic from passing over it.
  public void unreserve();
}
