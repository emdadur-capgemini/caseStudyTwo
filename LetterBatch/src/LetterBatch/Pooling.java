package LetterBatch;

import static java.nio.file.StandardWatchEventKinds.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
 
public class Pooling {
 
    private final WatchService watcher;
    private final Map<WatchKey, Path> keys;
    private final String fileName = "";
 
   
     // Creates a WatchService and registers the given directory
    Pooling(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey, Path>();
 
        directories(dir);
    }
    
    String abc;
    
    
    //File file;
    File file = new File("/home/regen/git/caseStudyTwo/LetterBatch/resources/COMPANYX.txt");
    

 
    
     // Register the given directory with the WatchService; This function will be called by FileVisitor  
    private void registerDirectory(Path dir) throws IOException
    {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        keys.put(key, dir);
        
		Reader read = new Reader(file);
		try {
			System.out.println("here");
			read.readFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
 


	// Register the given directory, and all its sub-directories, with the WatchService.
     
    private void directories(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirectory(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
 
    //Process all events for keys queued to the watcher

    void processEvents() {
        for (boolean check = true;check;) {
 
        	
            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }
 
            Path dir = keys.get(key);
 
            for (WatchEvent<?> event : key.pollEvents()) {
                @SuppressWarnings("rawtypes")
                WatchEvent.Kind kind = event.kind();
 
                // Context for directory entry event is the file name of entry
                @SuppressWarnings("unchecked")
                Path name = ((WatchEvent<Path>)event).context();
                Path child = dir.resolve(name);
 
                System.out.format("%s: %s\n", event.kind().name(), child);
 
                // if directory is created, and watching recursively, then register it and its sub-directories
                if (kind == ENTRY_CREATE) {
                	
                	Reader read = new Reader(file);
            		try {
            			System.out.println("here");
            			read.readFile();
            		} catch (Exception e) {
            			e.printStackTrace();
            		}
            		
                    try {
                        if (Files.isDirectory(child)) {
                            directories(child);
                        }
                                            
                    } catch (IOException x) {

                    	System.out.println(x.getMessage());
                    	
                    }
                    
                    //System.out.println("/home/regen/git/caseStudyTwo/LetterBatch/resources/q.txt");
                    
                    if(child.toString().contains("q.txt")) {
                    	
                    	System.out.println("q found. Program terminated.");
                    	check=false;
                    }
                    
                    if(child.toString().startsWith("/home/regen/git/caseStudyTwo/LetterBatch/resources/COMPANY") 
                    		&& child.toString().endsWith(".txt")){
                    	
                    	System.out.println("read file: " + child);
                    	File abc = new File(child.toString());
                    	read = new Reader(abc);
                    	try {
							read.readFile();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    	
                    } else {
                    	
                    	System.out.println("faile file: " + child);

                    	
                    }
                    
                }
                
                
            }
 
            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);
 
                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }
 
}
