import javax.sound.sampled.Clip;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class SoundEffect {
   Clip clip;
   private boolean currentlyPlaying = false;
   
   public SoundEffect() {
   
   
   }
   
   public SoundEffect(String soundFileName) {
   
      try {
         File file = new File(soundFileName);
         AudioInputStream sound = AudioSystem.getAudioInputStream(file);
         clip = AudioSystem.getClip();
         clip.open(sound);
      
      }
      catch(Exception e) {
         throw new IllegalArgumentException("The audio file you are trying to play does not exist");
      
      }
   }
   


   
   public void setFile(String soundFileName) {
   
      try {
         File file = new File(soundFileName);
         AudioInputStream sound = AudioSystem.getAudioInputStream(file);
         clip = AudioSystem.getClip();
         clip.open(sound);
      
      }
      catch(Exception e) {
         throw new IllegalArgumentException("The audio file you are trying to play does not exist");
      
      }
   }
   
   public void play() {
      try {
         if (currentlyPlaying) {
         
         }
         else {
            currentlyPlaying = true;
            clip.setFramePosition(0);
            clip.start();
            Thread.sleep(5);
            currentlyPlaying = false;
         }
      
      } 
      catch (Exception D) {
      
      
      }
   }
}