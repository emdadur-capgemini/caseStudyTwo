package letterBatchOO;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

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


public class Polling {

	private final WatchService watcher;
	private final Map<WatchKey, Path> keys;
	private final String fileName = "";
	static File file;
	static Path path;
	private Reader inputFile;

	// Creates a WatchService and registers the given directory
	Polling(Path dir) throws IOException {
		this.watcher = FileSystems.getDefault().newWatchService();
		this.keys = new HashMap<WatchKey, Path>();

		directories(dir);
	}

	// File file;

	// File file = new
	// File("/home/regen/git/caseStudyTwo/LetterBatch/resources/input/COMPANYX.txt");

	// Register the given directory with the WatchService; This function will be
	// called by FileVisitor
	private void registerDirectory(Path dir) throws IOException {
		WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		keys.put(key, dir);

		File folder = new File("/home/regen/git/caseStudyTwo/LetterBatch/resources/input/");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {

				if (listOfFiles[i].getName().toString().startsWith("COMPANY")
						&& listOfFiles[i].getName().toString().endsWith(".txt")) {

					file = new File(listOfFiles[i].getPath());

					path = Paths.get(listOfFiles[i].getAbsolutePath());

					inputFile = new Reader(path);
					if(inputFile.isValidFile())
						inputFile.generateAllLetters();
					
				
				}

			} else {

				System.out.println("Incorrect File Format.");

			}
		}

	}

	// Register the given directory, and all its sub-directories, with the
	// WatchService.

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

	// Process all events for keys queued to the watcher

	void processEvents() {

		for (boolean check = true; check;) {

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
				Path name = ((WatchEvent<Path>) event).context();
				Path child = dir.resolve(name);

				// if directory is created, and watching recursively, then register it and its
				// sub-directories
				if (kind == ENTRY_CREATE) {

					try {
						if (Files.isDirectory(child)) {
							directories(child);
						}

					} catch (IOException x) {

						System.out.println(x.getMessage());

					}

					if (child.toString().contains("q.txt")) {

						System.out.println("q.txt found. Program terminated.");
						check = false;
					}

					if (child.toString().startsWith("/home/regen/git/caseStudyTwo/LetterBatch/resources/input/COMPANY")
							&& child.toString().endsWith(".txt")) {

						try {
							inputFile = new Reader(child);
							if(inputFile.isValidFile())
								inputFile.generateAllLetters();
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						

					} else {

						System.out.println("Move to error folder" + child);

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
