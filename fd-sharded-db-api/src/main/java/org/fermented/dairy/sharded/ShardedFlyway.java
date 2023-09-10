package org.fermented.dairy.sharded;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;


/**
 * Flyway migration implementation for sharded DBs (with a core schema).
 *
 * @author Fermented Dairy
 */
public class ShardedFlyway {

    private final Path corePath;

    private final Path shardPath;

    /**
     * Constructor using {@link String} paths to folders containing core and shard migrations.
     *
     * @param coreFolder The path to the folder containing the core migrations
     * @param shardedFolder The path to the folder containing the sharded migrations
     */
    public ShardedFlyway(final String coreFolder, final String shardedFolder) {
        this.corePath = validatePath(
                Path.of(Objects.requireNonNull(coreFolder, "coreFolder must be provided"))
        );
        this.shardPath = validatePath(
                Path.of(Objects.requireNonNull(shardedFolder, "shardedFolder must be provided"))
        );
    }

    /**
     * Constructor using {@link File } folders to folders containing core and shard migrations.
     *
     * @param coreFolder The path to the folder containing the core migrations
     * @param shardedFolder The path to the folder containing the sharded migrations
     */
    public ShardedFlyway(final File coreFolder, final File shardedFolder) {
        this.corePath = validatePath(Objects.requireNonNull(coreFolder, "coreFolder must be provided").toPath());
        this.shardPath = validatePath(Objects.requireNonNull(shardedFolder, "shardedFolder must be provided").toPath());
    }

    /**
     * Constructor using {@link Path } paths to folders containing core and shard migrations.
     *
     * @param corePath The path to the folder containing the core migrations
     * @param shardPath The path to the folder containing the sharded migrations
     */
    public ShardedFlyway(final Path corePath, final Path shardPath) {
        this.corePath = validatePath(corePath);
        this.shardPath = validatePath(shardPath);
    }

    private Path validatePath(final Path path) {
        Objects.requireNonNull(path, "Path must be provided");

        if (!path.toFile().exists()) {
            throw new IllegalArgumentException("Path '%s' must exist".formatted(path.toString()));
        }

        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException("Path '%s' is not a directory".formatted(path.toString()));
        }
        return path;
    }
}
