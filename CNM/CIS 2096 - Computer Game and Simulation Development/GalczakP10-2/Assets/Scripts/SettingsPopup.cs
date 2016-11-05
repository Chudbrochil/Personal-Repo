using UnityEngine;
using System.Collections;

public class SettingsPopup : MonoBehaviour {

    [SerializeField]
    private AudioClip sound;

    public void OnSoundToggle()
    {
        Managers.Audio.PlaySound(sound);
        Managers.Audio.soundMute = !Managers.Audio.soundMute;
    }

    public void OnSoundValue(float volume)
    {
        Managers.Audio.soundVolume = volume;
    }

    public void OnPlayMusic(int selector)
    {
        Managers.Audio.PlaySound(sound);

        // Deciding which music to play, within Unity there are set fields 1-3 for the music
        switch (selector)
        {
            case 1:
                Managers.Audio.PlayIntroMusic();
                break;
            case 2:
                Managers.Audio.PlayLevelMusic();
                break;
            case 3:
                Managers.Audio.PlayPanting();
                break;
            default:
                Managers.Audio.StopMusic();
                break;
        }
    }

    // Turning the music on
    public void OnMusicToggle()
    {
        Managers.Audio.musicMute = !Managers.Audio.musicMute;
        Managers.Audio.PlaySound(sound);
    }

    public void OnMusicValue(float volume)
    {
        Managers.Audio.musicVolume = volume;
    }



}
