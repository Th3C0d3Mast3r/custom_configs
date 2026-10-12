# Extensions
The following plugins, extensions, and Model Context Protocol (MCP) tools enhance the capabilities of CLI-based LLMs (such as Claude Code, OpenCode, Gemini CLI, and Codex CLI) across context retrieval, execution, code quality, and infrastructure.

## Context & Codebase Intelligence

- **[Ponytail](https://ponytail.dev/)** — Prompt/ruleset extension that forces CLI agents to write clean, minimal, non-verbose, and maintainable production code.
- **[Graphify](https://github.com/Graphify-Labs/graphify)** — Constructs local, lightweight knowledge graphs from your codebase, docs, and PDFs to slash token consumption during context building.
- **[Context7](https://context7.com/)** — Injects live library documentation and current API specifications directly into the agent’s context window to eliminate syntax hallucinations.
- **[ast-grep](https://ast-grep.github.io/)** — Provides AST-based code search and structural refactoring tools to agents, replacing fragile regex string matching with precise syntactic awareness.
- **[ripgrep (`rg`)](https://github.com/BurntSushi/ripgrep)** — Ultra-fast regex-based file search plugin enabling agents to query massive monorepos in milliseconds.
- **[tree-sitter-cli](https://tree-sitter.github.io/tree-sitter/)** — Generates concrete syntax trees (CSTs) for CLI agents, allowing them to map symbol scopes, class hierarchies, and function boundaries accurately.
- **[caveman](https://github.com/juliusbrussee/caveman)** - A plugin for token utilization reduction for local LLMs.
- **[rtk - Reduce Token Kit](https://www.rtk-ai.app/)** - is an alternative for the caveman

## System, Shell & Version Control

- **[GitHub MCP Server](https://github.com/modelcontextprotocol/servers/tree/main/src/github)** — Official MCP server giving agents access to PR creation, issue tracking, CI/CD pipeline checks, and code reviews.
- **[Postgres MCP Server](https://github.com/modelcontextprotocol/servers/tree/main/src/postgres)** — Grants LLM agents safe, inspectable, read-only or read-write access to local/remote PostgreSQL schemas, queries, and migrations.
- **[Docker/Container MCP](https://github.com/mcp-docker/mcp-server-docker)** — Allows CLI agents to list containers, inspect images, run ephemeral test containers, and read real-time service logs directly.
- **[GitLab CLI Integration](https://gitlab.com/gitlab-org/cli)** — Extension wrapper allowing agents to manage MRs, inspect pipeline status, and run runner jobs natively in terminal workflows.
- **[SQLite MCP Server](https://github.com/modelcontextprotocol/servers/tree/main/src/sqlite)** — Enables agents to inspect, query, and perform migrations on local SQLite database files with direct structural feedback.

## Web Search, Scraping & Real-Time Context

* **[Firecrawl CLI / MCP](https://github.com/firecrawl/cli)** — Converts web pages and documentation sites into clean, token-efficient Markdown output for live web access.
* **[Brave Search MCP Server](https://github.com/modelcontextprotocol/servers/tree/main/src/brave-search)** — Grants privacy-centric, rate-limit-friendly real-time web search capabilities to CLI LLMs without tracking.
* **[Fetch MCP](https://github.com/modelcontextprotocol/servers/tree/main/src/fetch)** — Lightweight URL-fetching extension optimized to extract raw HTML/text content into LLM context windows.
* **[Puppeteer / Playwright MCP](https://github.com/modelcontextprotocol/servers/tree/main/src/puppeteer)** — Headless browser execution plugin for UI verification, DOM inspection, and automated end-to-end web testing.

## Security, Monitoring & Infrastructure

* **[Snyk / Trivy Security Scanner](https://github.com/aquasecurity/trivy)** — Injects container vulnerability analysis and dependency vulnerability scans directly into the agent’s review pipeline.
* **[Rsyslog / Log Analysis MCP](https://github.com/modelcontextprotocol)** — Ingests localized application logs, system journals, and stack traces into agent context for automated root-cause analysis.
* **[Ansible-Lint & Terraform Inspect](https://github.com/ansible/ansible-lint)** — Static analysis tools integrated for agents working on Infrastructure-as-Code (IaC) verification and dry-runs.
* **[Kubernetes (`kubectl`) MCP Adapter](https://github.com/modelcontextprotocol)** — Grants CLI agents restricted inspection rights to pod logs, deployment statuses, and cluster event queues.

## Productivity, Workflow & Memory

* **[Memory / Knowledge Graph MCP](https://github.com/modelcontextprotocol/servers/tree/main/src/memory)** — Provides persistent long-term memory across terminal sessions using an open graph database structure.
* **[Google Calendar / Workspace MCP](https://github.com/modelcontextprotocol)** — Integrates calendar events, schedule availability, and workspace documentation into daily developer agent routines.
* **[Slack / Discord Webhooks MCP](https://github.com/modelcontextprotocol)** — Injects messaging triggers, allowing agents to push build summaries, test passes, and deployment alerts automatically.
* **[Linear MCP Server](https://github.com/jerhadf/linear-mcp-server)** — Connects developer agents to Linear ticket systems for automated task updates, issue assignment, and status syncs.
* **[Notion MCP Server](https://github.com/sue445/mcp-server-notion)** — Allows CLI agents to read and append technical documentation, runbooks, and PRDs directly within Notion workspaces.
* **[Obsidian Vault MCP](https://github.com/calclavia/mcp-obsidian)** — Connects agents directly to local Markdown-based personal knowledge management (PKM) vaults for local docs synchronization.