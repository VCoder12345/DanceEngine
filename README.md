# Dance Engine

A reusable 2D game engine written entirely in Java, developed from scratch with minimal external dependencies. The engine provides the core systems needed to build 2D games, including entity management, animation, audio, resources, input, AI, and map loading.

## Features

* **Entity Component System:** Component-based entity architecture for game objects.
* **Behavior trees:** Reusable behavior-tree system for AI logic.
* **Animation:** State-machine-based animation logic for managing animation states and transitions.
* **Resource management:** Centralized loading and management of game assets.
* **Tiled integration:** Import and use maps created with the [Tiled Map Editor](https://www.mapeditor.org/).
* **Audio:** Sound effect and music playback.
* **Input:** Keyboard and mouse input, with controller support through an external Java library.
* **Event system:** Decoupled communication between engine and game systems.

## Architecture

The engine is structured as a collection of reusable systems rather than being tied to a single game. Core functionality such as entity management, animation, AI, resource loading, input, and audio is separated into independent components that can be combined by individual games.

Most of the engine is implemented using only the Java standard library. The main external dependency is used for controller input.

## Background

This project was an early exploration of game-engine architecture and reusable software systems. It was developed entirely in Java and served as a foundation for experimenting with ECS, AI, resource management, event-driven design, and game-state architecture.
