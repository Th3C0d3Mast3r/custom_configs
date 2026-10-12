# Multi-Agent and Local LLMs
Running locally present LLM is something that would require some good amount of RAM and GPU presence- along with some SSD space of few 20 to 30GBs I would say. So, these are a few locally running LLMs and Multi-Agent orchestration frameworks/things that you can use and refer to for the things on your laptop.

## LOCAL INFERENCE ENGINES (Models on Device)
- **Ollama** - A standard local-model runner for open models. Works best for serving local GGUF models on Apple Silicon (Metal) or NVIDIA w/ zero manual setup. Connects directly to the CLI tools like OpenCode and Aider.
- **vLLM** - 