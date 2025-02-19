<p align="center">
	<img src="data:https://i.ibb.co/pjj4GGbg/Factions-of-the-Fractured-Realm.webp" alt="fractured-mod-banner" width="800">
</p>
<p align="center">
	<em><code>❯ REPLACE-ME</code></em>
</p>
<p align="center">
	<img src="https://img.shields.io/github/license/Bobisawesome07/fractured-mod?style=plastic&logo=opensourceinitiative&logoColor=white&color=0059ff" alt="license">
	<img src="https://img.shields.io/github/last-commit/Bobisawesome07/fractured-mod?style=plastic&logo=git&logoColor=white&color=0059ff" alt="last-commit">
	<img src="https://img.shields.io/github/languages/top/Bobisawesome07/fractured-mod?style=plastic&color=0059ff" alt="repo-top-language">
	<img src="https://img.shields.io/github/languages/count/Bobisawesome07/fractured-mod?style=plastic&color=0059ff" alt="repo-language-count">
</p>
<p align="center">Built with the tools and technologies:</p>
<p align="center">
	<img src="https://img.shields.io/badge/Gradle-02303A.svg?style=plastic&logo=Gradle&logoColor=white" alt="Gradle 8.11">
	<img src="https://img.shields.io/badge/GitHub%20Actions-2088FF.svg?style=plastic&logo=GitHub-Actions&logoColor=white" alt="GitHub%20Actions">
	<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=plastic&logo=openjdk&logoColor=white" alt="java 17.0.2">
</p>
<br>

## 🔗 Quick Links

- [📍 Overview](#-overview)
- [👾 Features](#-features)
- [📂 Project Index](#-project-index)
- [🚀 Getting Started](#-getting-started)
  - [☑️ Prerequisites](#-prerequisites)
  - [⚙️ Installation](#-installation)
- [🔰 Contributing](#-contributing)
- [🎗 License](#-license)
- [🙌 Acknowledgments](#-acknowledgments)

---

## 📍 Overview

Fratured Mod is a Minecraft mod built using the Fabric modding toolchain. This mod aims to enhance the gameplay experience by introducing new features and improvements.

---

## 👾 Features

- **Fake Bedrock Block**: A block that looks like bedrock but has different properties.
- **Portal Sword**: A sword that can open a portal to a pocket dimension.

---

### 📂 Project Index
<details open>
	<summary><b><code>FRACTURED-MOD/</code></b></summary>
	<details> <!-- __root__ Submodule -->
		<summary><b>__root__</b></summary>
		<blockquote>
			<table>
			<tr>
				<td><b><a href='https://github.com/Bobisawesome07/fractured-mod/blob/bob/gradlew.bat'>gradlew.bat</a></b></td>
				<td><code>Windows batch file for building the project.</code></td>
			</tr>
			<tr>
				<td><b><a href='https://github.com/Bobisawesome07/fractured-mod/blob/bob/build.gradle'>build.gradle</a></b></td>
				<td><code>Gradle build script with project dependencies and tasks.</code></td>
			</tr>
			<tr>
				<td><b><a href='https://github.com/Bobisawesome07/fractured-mod/blob/bob/settings.gradle'>settings.gradle</a></b></td>
				<td><code>Settings for the Gradle build.</code></td>
			</tr>
			<tr>
				<td><b><a href='https://github.com/Bobisawesome07/fractured-mod/blob/bob/README.md'>README.md</a></b></td>
				<td><code>Project README with detailed information.</code></td>
			</tr>
			</table>
		</blockquote>
	</details>
	<details> <!-- .github Submodule -->
		<summary><b>.github</b></summary>
		<blockquote>
			<details>
				<summary><b>workflows</b></summary>
				<blockquote>
					<table>
					<tr>
						<td><b><a href='https://github.com/Bobisawesome07/fractured-mod/blob/bob/.github/workflows/build.yml'>build.yml</a></b></td>
						<td><code>GitHub Actions workflow for building the project.</code></td>
					</tr>
					</table>
				</blockquote>
			</details>
		</blockquote>
	</details>
	<details> <!-- src Submodule -->
		<summary><b>src</b></summary>
		<blockquote>
			<details>
				<summary><b>main</b></summary>
				<blockquote>
					<details>
						<summary><b>resources</b></summary>
						<blockquote>
							<table>
							<tr>
								<td><b><a href='https://github.com/Bobisawesome07/fractured-mod/blob/bob/src/main/resources/fractured-mod.mixins.json'>fractured-mod.mixins.json</a></b></td>
								<td><code>Mixin configuration for the mod.</code></td>
							</tr>
							<tr>
								<td><b><a href='https://github.com/Bobisawesome07/fractured-mod/blob/bob/src/main/resources/fabric.mod.json'>fabric.mod.json</a></b></td>
								<td><code>Fabric mod metadata.</code></td>
							</tr>
							</table>
						</blockquote>
					</details>
					<details>
						<summary><b>java</b></summary>
						<blockquote>
							<details>
								<summary><b>com</b></summary>
								<blockquote>
									<details>
										<summary><b>fofr</b></summary>
										<blockquote>
											<table>
											<tr>
												<td><b><a href='https://github.com/Bobisawesome07/fractured-mod/blob/bob/src/main/java/com/fofr/FracturedMod.java'>FracturedMod.java</a></b></td>
												<td><code>Main class for the mod.</code></td>
											</tr>
											</table>
											<details>
												<summary><b>mixin</b></summary>
												<blockquote>
													<table>
													<tr>
														<td><b><a href='https://github.com/Bobisawesome07/fractured-mod/blob/bob/src/main/java/com/fofr/mixin/ExampleMixin.java'>ExampleMixin.java</a></b></td>
														<td><code>Example mixin class for the mod.</code></td>
													</tr>
													</table>
												</blockquote>
											</details>
										</blockquote>
									</details>
								</blockquote>
							</details>
						</blockquote>
					</details>
				</blockquote>
			</details>
			<details>
				<summary><b>client</b></summary>
				<blockquote>
					<details>
						<summary><b>resources</b></summary>
						<blockquote>
							<table>
							<tr>
								<td><b><a href='https://github.com/Bobisawesome07/fractured-mod/blob/bob/src/client/resources/fractured-mod.client.mixins.json'>fractured-mod.client.mixins.json</a></b></td>
								<td><code>Client-side mixin configuration for the mod.</code></td>
							</tr>
							</table>
						</blockquote>
					</details>
					<details>
						<summary><b>java</b></summary>
						<blockquote>
							<details>
								<summary><b>com</b></summary>
								<blockquote>
									<details>
										<summary><b>fofr</b></summary>
										<blockquote>
											<table>
											<tr>
												<td><b><a href='https://github.com/Bobisawesome07/fractured-mod/blob/bob/src/client/java/com/fofr/FracturedModClient.java'>FracturedModClient.java</a></b></td>
												<td><code>Client entry point for the mod.</code></td>
											</tr>
											<tr>
												<td><b><a href='https://github.com/Bobisawesome07/fractured-mod/blob/bob/src/client/java/com/fofr/FracturedModDataGenerator.java'>FracturedModDataGenerator.java</a></b></td>
												<td><code>Data generator class for the mod.</code></td>
											</tr>
											</table>
											<details>
												<summary><b>mixin</b></summary>
												<blockquote>
													<details>
														<summary><b>client</b></summary>
														<blockquote>
															<table>
															<tr>
																<td><b><a href='https://github.com/Bobisawesome07/fractured-mod/blob/bob/src/client/java/com/fofr/mixin/client/ExampleClientMixin.java'>ExampleClientMixin.java</a></b></td>
																<td><code>Example client-side mixin class for the mod.</code></td>
															</tr>
															</table>
														</blockquote>
													</details>
												</blockquote>
											</details>
										</blockquote>
									</details>
								</blockquote>
							</details>
						</blockquote>
					</details>
				</blockquote>
			</details>
		</blockquote>
	</details>
</details>

---
## 🚀 Getting Started

### ☑️ Prerequisites

Before getting started with Fratured Mod, ensure your runtime environment meets the following requirements:

- **Programming Language:** Java
- **Package Manager:** Gradle

### ⚙️ Installation

Install Fratured Mod using one of the following methods:

**Build from source:**

1. Clone the Fratured Mod repository:
```sh
❯ git clone https://github.com/Bobisawesome07/fractured-mod
```

2. Navigate to the project directory:
```sh
❯ cd fractured-mod
```

3. Install the project dependencies:


**Using `gradle`** &nbsp; [<img align="center" src="https://img.shields.io/badge/Gradle-02303A.svg?style=plastic&logo=gradle&logoColor=white" />](https://gradle.org/)

```sh
❯ gradle build
```

**Using Fabric:**

1. Ensure you have the Fabric Loader installed. You can download it from [Fabric website](https://fabricmc.net/use/).
2. Download the latest release of Fratured Mod from the [releases page](https://github.com/Bobisawesome07/fractured-mod/releases).
3. Place the downloaded `.jar` file into the `mods` folder of your Minecraft directory.

---
## 🔰 Contributing

- **💬 [Join the Discussions](https://github.com/Bobisawesome07/fractured-mod/discussions)**: Share your insights, provide feedback, or ask questions.
- **🐛 [Report Issues](https://github.com/Bobisawesome07/fractured-mod/issues)**: Submit bugs found or log feature requests for the `Fratured Mod` project.
- **💡 [Submit Pull Requests](https://github.com/Bobisawesome07/fractured-mod/blob/main/CONTRIBUTING.md)**: Review open PRs, and submit your own PRs.

<details closed>
<summary>Contributing Guidelines</summary>

1. **Fork the Repository**: Start by forking the project repository to your GitHub account.
2. **Clone Locally**: Clone the forked repository to your local machine using a git client.
   ```sh
   git clone https://github.com/Bobisawesome07/fractured-mod
   ```
3. **Create a New Branch**: Always work on a new branch, giving it a descriptive name.
   ```sh
   git checkout -b new-feature-x
   ```
4. **Make Your Changes**: Develop and test your changes locally.
5. **Commit Your Changes**: Commit with a clear message describing your updates.
   ```sh
   git commit -m 'Implemented new feature x.'
   ```
6. **Push to GitHub**: Push the changes to your forked repository.
   ```sh
   git push origin new-feature-x
   ```
7. **Submit a Pull Request**: Create a PR against the original project repository. Clearly describe the changes and their motivations.
8. **Review**: Once your PR is reviewed and approved, it will be merged into the main branch. Congratulations on your contribution!
</details>

<details closed>
<summary>Contributor Graph</summary>
<br>
<p align="left">
   <a href="https://github.com/Bobisawesome07/fractured-mod/graphs/contributors">
      <img src="https://contrib.rocks/image?repo=Bobisawesome07/fractured-mod">
   </a>
</p>
</details>

---

## 🎗 License

This project is licensed under the CC0 1.0 Universal (CC0 1.0) Public Domain Dedication. See the [LICENSE](LICENSE) file for more information.

---

## 🙌 Acknowledgments

- Thank you to Sir.Duckie for creating most of the models and textures for the mod.

```` ▋
